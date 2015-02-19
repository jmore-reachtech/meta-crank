DESCRIPTION = "Crank runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

INSANE_SKIP_${PN} = "ldflags textrel staticdev"
INSANE_SKIP_${PN}-dev = "ldflags textrel staticdev"
INSANE_SKIP_${PN}-staticdev = "ldflags textrel staticdev"
INHIBIT_PACKAGE_DEBUG_SPLIT="1"

DEPENDS += "gstreamer"

PR = "r1"

SRC_URI = "file://${P}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/application/src
    
    cp -rf ${S}/crank-demo-2.0/* ${D}/application/src
}

FILES_${PN} += "/application/src"
