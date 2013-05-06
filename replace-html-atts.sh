#!/bin/bash

# Replaces all html attributes like {:att => ""} by the html form
sed -ie 's/{:/\(/g' $1
sed -ie 's/{"/\(/g' $1
sed -ie 's/" => /=/g' $1
sed -ie 's/ => /=/g' $1
sed -ie 's/", "/" /g' $1
sed -ie 's/, :/ /g' $1
sed -ie 's/}/\)/g' $1
sed -ie 's/)\//)/g' $1
