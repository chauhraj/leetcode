#!/usr/bin/env bash

function usage() {
    echo "newProblem <leet-code-Id> <methodName> <type-of-the-input> <output-type>"
    exit 0;
}

if [[ $# -ne 4 ]]; then
    usage ;
fi

gradle -q newProblem -PleetId=$1 -Pmethod=$2 -PinputType=$3 -PoutputType=$4
