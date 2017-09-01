import $ivy.`org.jsoup:jsoup:1.10.3`
import $ivy.`com.typesafe.akka::akka-http-spray-json:10.0.9`
import $ivy.`com.github.javaparser:javaparser-core:3.2.10`

// Download the HTML
import javax.script._
import jdk.nashorn.api.scripting._

import com.github.javaparser._
import ast._
import ast.body._

import spray.json._
import org.jsoup._
import org.jsoup.nodes._
import scala.collection.JavaConverters._

import spray.json._
import DefaultJsonProtocol._

def as[T](a: Any): T = a.asInstanceOf[T]
implicit class RichObjectOps(val o: Any) extends AnyVal {
    def as[T]: T = o.asInstanceOf[T]t
}

def fetchLeetCodeProblems(category: String = "algorithms"): Map[String, String] = {
    val json = scala.io.Source.fromURL(s"https://leetcode.com/api/problems/$category/").mkString.parseJson.asJsObject
    json.fields("stat_status_pairs")
        .asInstanceOf[JsArray].elements
                              .map(_.asJsObject.fields("stat").asJsObject.fields)
                              .map(m => m("question_id").toString -> m("question__title_slug").asInstanceOf[JsString].value).toMap
} 

def extractJavascript(htmlDoc: Document): String = {
    val js = htmlDoc.select("script:not([^type])").select(":not([^src])")
    val script = js.asScala.flatMap(_.dataNodes.asScala.map(_.getWholeData.trim)).filter(_.startsWith("var")).head 
    s"""function pgData() {
        $script
        return pageData;
    }"""
}

def executeJavascript(script: String): JSObject = {
    val jsEngine = new ScriptEngineManager().getEngineByMimeType("text/javascript") 
    val compiler = jsEngine.as[Compilable]
    val compiled = compiler.compile(script)
    compiled.eval().as[JSObject].newObject().as[JSObject]
} 

def extractLanguageBasedCode(result: JSObject): Map[String, String] = {
   result.getMember("codeDefinition")
        .as[JSObject]
        .values
        .asScala
        .map { o => 
              val jsObj = o.as[JSObject]
              jsObj.getMember("value").as[String] -> jsObj.getMember("defaultCode").as[String]
        }.toMap 
} 

type LeetCodeResult = (String, String)
def leetcodePbMetadata(problemSlug: String, lang: String = "java"): LeetCodeResult = {
    val htmlDoc = Jsoup.connect(s"https://leetcode.com/problems/$problemSlug/#/description")
                       .userAgent("""Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36""")
                       .get
    val description = htmlDoc.select(".question-description:eq(1)").last.text

    val f: Document => Map[String, String] = extractJavascript _ andThen executeJavascript andThen extractLanguageBasedCode
    val results = f(htmlDoc)
    (description, results(lang))
    //(description, htmlDoc)
}

@main
def setupProblem(leetCodePbId: Int): ClassOrInterfaceDeclaration = {
  val slug = fetchLeetCodeProblems()(leetCodePbId.toString)
  val (_, code) = leetcodePbMetadata(slug)

  val parser = JavaParser.parse(code)
  val clz = parser.getChildNodesByType(classOf[ClassOrInterfaceDeclaration]).asScala.head
  clz.setName(s"Pb${leetCodePbId}Solution")
  clz

}
