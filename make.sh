#!/bin/bash
# find . -regex ".*\(aux\|bbl\|blg\|brf|\idx\|ilg\|ind\|lof\|log\|lol\|lot\|out\|nav\|out\|snm\|tdo\|toc\|fls\|fdb_latexmk\)$" -exec rm -i {} \;
lualatex --shell-escape tcr-main.tex
