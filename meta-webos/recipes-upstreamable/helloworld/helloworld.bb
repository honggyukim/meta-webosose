SUMMARY = "Hello World"
LICENSE = "CLOSED"

SRC_URI = "file://helloworld.c"
S = "${WORKDIR}"

do_compile() {
    ${CC} -o helloworld helloworld.c
}
