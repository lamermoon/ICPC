function trim(s)
  return (s:gsub("^%s*(.-)%s*$", "%1"))
end

-- parse a algorithm file
function parseAlgorithm(dir, algorithm)
    tex.print("\\begin{algo}")

    stateNum     = 0
    description = ""
    info        = ""
    start       = false

    os.execute("touch " .. dir .. "tmp_" .. algorithm)  -- create file to dump code

    for line in io.open(dir .. algorithm):lines() do
        if (stateNum == 0) then -- first comment line is the name (right after '/* ')
           tex.print("\\setAlgorithmName{" .. string.sub(line, 4) .. "}")
           stateNum = stateNum+1
        elseif stateNum == 1 then -- followed by multiple description lines
            line = trim(line)
            if string.sub(line,0,2) == "**" then -- complexity annotation like ** n^2
                tex.print("\\setAlgorithmComplexity{" .. string.sub(line, 3) .. "}")
                -- texio.write_nl(string.sub(line,3))
            elseif line == "*/" then
                tex.print("\\setAlgorithmDescription{" .. description .. "}")
                stateNum = stateNum+1
            else
                description = description .. " " .. string.sub(line, 3)
            end
        else -- now we are in the code
            if trim(line) == "//END" then   -- disable output from this line on
                start = false
            end
            if start == true then           -- if we are outputting, dump line into temp file
                os.execute("echo '" .. line .. "' >> " .. dir .. "tmp_" .. algorithm)
            end
            if trim(line) == "//START" then -- enable output from next line on
                start = true
            end
        end
    end

    tex.print("\\setAlgorithmFile{" .. dir .. "tmp_" .. algorithm .. "}")
    -- calculate MD5 sum of code snippet without the whitespace
    local md5val = io.popen('cat ' .. dir .. "tmp_" .. algorithm
                            .. ' | tr -d [:space:] | md5sum | sed "s/\\s.\\+//"'):lines()
    tex.print("\\setAlgorithmHash{" .. md5val() .. "}")
    tex.print("\\end{algo}")
end

-- parse all the algorithms in that dir
function parseDir(dir)
    tex.print("\\section{" .. string.sub(dir, 0, string.len(dir)-1) .. "}")
    -- for algorithm in io.popen('ls -a "'.. dir ..'"'):lines() do
    for algorithm in io.popen('find "'.. dir ..'" -name "*.java" -printf "%f\\n" | sort'):lines() do
        parseAlgorithm(dir, algorithm)
    end
end


-- find all dirs in the script folder
function parse()
    for dir in io.popen('ls -d */', 'r'):lines() do
        if (dir ~= "img/" and dir ~= "disabled/") then
           parseDir(dir)
        end
    end
end

-- remove tmp files if found
function removeTmpFiles()
    for dir in io.popen('ls -d */', 'r'):lines() do
        if (dir ~= "img/") then
            for algorithm in io.popen('ls -a "'.. dir ..'"tmp_* 2>/dev/null'):lines() do
                os.execute("rm " .. algorithm)
            end
        end
    end
end
