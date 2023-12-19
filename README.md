Simple TCP server written in Kotlin to read line delimited JSON from a socket and print the stdout.

## Build
```shell
mvn package
```

## Usage

```shell
Usage: print-server [<options>]

Options:
  -n, --host=<text>  The server host name
  -p, --port=<int>   server port number
  -v, --verbose      enable verbose mode
  -h, --help         Show this message and exit
```