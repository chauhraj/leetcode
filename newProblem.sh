#!/usr/bin/env bash

function usage() {
    echo "newProblem <leet-code-Id> <methodName> <type-of-the-input> <output-type>"
    exit 0;
}

if [[ $# -ne 4 ]]; then
    usage ;
fi

BASE_URL="https://leetcode.com"
BRANCH=$(git symbolic-ref --short HEAD)
PROBLEM_NAME=$(wget -qO- $BASE_URL/problemset/algorithms/ | grep -A 3 "<td>${1}</td>" | sed -n "s/.*href=['\"]\([^'\"]*\)['\"].*/\1/gp" | awk -F / '{print $3}')
if [[ "$BRANCH" == "master" ]]; then
    git checkout -b "pb${1}/${PROBLEM_NAME}"
fi
MAIN_PROBLEM="${BASE_URL}/problems/$PROBLEM_NAME"
gradle -q newProblem -PleetId=$1 -Pmethod=$2 -PinputType=$3 -PoutputType=$4
