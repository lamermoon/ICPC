(TeX-add-style-hook
 "tcr-main"
 (lambda ()
   (TeX-add-to-alist 'LaTeX-provided-class-options
                     '(("article" "10pt" "a4paper" "ngerman" "oneside" "")))
   (TeX-add-to-alist 'LaTeX-provided-package-options
                     '(("xparse" "log-declarations=false") ("luainputenc" "utf8") ("fontenc" "T1") ("babel" "english") ("hyperref" "unicode" "pdfencoding=auto" "hidelinks") ("geometry" "left=1.5cm" "right=.75cm" "top=2.0cm" "bottom=.8cm")))
   (add-to-list 'LaTeX-verbatim-environments-local "lstlisting")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "lstinline")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperref")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperimage")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "hyperbaseurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "nolinkurl")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "url")
   (add-to-list 'LaTeX-verbatim-macros-with-braces-local "path")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "lstinline")
   (add-to-list 'LaTeX-verbatim-macros-with-delims-local "path")
   (TeX-run-style-hooks
    "latex2e"
    "article"
    "art10"
    "xparse"
    "luainputenc"
    "luacode"
    "fontenc"
    "fontspec"
    "babel"
    "hyperref"
    "geometry"
    "fancyhdr"
    "lastpage"
    "mathtools"
    "amsfonts"
    "amssymb"
    "algorithm"
    "algorithmic"
    "graphicx"
    "pdfpages"
    "xcolor"
    "listings"
    "multicol")
   (TeX-add-symbols
    '("hash" 1)
    '("setAlgorithmHash" 1)
    '("setAlgorithmComplexity" 1)
    '("setAlgorithmFile" 1)
    '("setAlgorithmDescription" 1)
    '("setAlgorithmName" 1)
    '("bigo" 1)
    "teamname"
    "teammembers"
    "university"
    "N"
    "lcm"
    "algorithmLanguage"
    "algorithmName"
    "algorithmDescription"
    "algorithmFile"
    "algorithmComplexity"
    "algorithmHash")
   (LaTeX-add-environments
    "java"
    "cpp"
    "python")
   (LaTeX-add-xcolor-definecolors
    "darkgreen"
    "uni"
    "black"
    "violet"
    "black!5"
    "red"))
 :latex)

