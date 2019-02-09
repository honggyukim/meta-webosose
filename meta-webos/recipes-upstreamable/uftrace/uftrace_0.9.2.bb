SUMMARY = "Trace and analyze execution of a program written in C/C++"
HOMEPAGE = "https://github.com/namhyung/uftrace"
BUGTRACKER = "https://github.com/namhyung/uftrace/issues"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS_append_libc-musl = " argp-standalone"

inherit autotools

PACKAGECONFIG ??= "dw"
PACKAGECONFIG[dw] = ",--without-libelf,elfutils"
PACKAGECONFIG[python] = ",--without-libpython,python"
PACKAGECONFIG[ncurses] = ",--without-libncurses,ncurses"

# v0.9.2
SRCREV = "15e5bef18cc7d85b35b4a864e12697c1c0d32772"
SRC_URI = "git://github.com/namhyung/${BPN}"
S = "${WORKDIR}/git"

LDFLAGS_append_libc-musl = " -largp"

def set_target_arch(d):
    import re
    arch = d.getVar('TARGET_ARCH', True)
    if re.match(r'i.86', arch, re.I):
        return 'i386'
    else:
        return arch

EXTRA_OECONF_append = " ARCH=${@set_target_arch(d)}"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/*.so"

COMPATIBLE_HOST = "(i.86|x86_64|aarch64|arm)"

# uftrace supports armv6 and above
COMPATIBLE_HOST_armv4 = 'null'
COMPATIBLE_HOST_armv5 = 'null'
