PROGRAM hello (output);

{write 'Hello, world.' ten times.}

VAR
    i : integer;

BEGIN {hello}
    for i := 1 to 10 DO BEGIN
        writeln('Hello, world.');
    END;
END {hello}.