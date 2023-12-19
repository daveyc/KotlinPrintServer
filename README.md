Simple TCP server written in Kotlin to read line delimited JSON from a socket and print the stdout.

## Build
```shell
mvn package
```

## Usage

```shell
> java -jar target/PrintServer2-1.0-SNAPSHOT-jar-with-dependencies.jar --help

Usage: print-server [<options>]

Options:
  -n, --host=<text>  The server host name
  -p, --port=<int>   server port number
  -v, --verbose      enable verbose mode
  -h, --help         Show this message and exit
```