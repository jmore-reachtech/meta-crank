DESCRIPTION = "Crank runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

INSANE_SKIP_${PN} = "ldflags textrel staticdev"
INSANE_SKIP_${PN}-dev = "ldflags textrel staticdev"
INSANE_SKIP_${PN}-staticdev = "ldflags textrel staticdev"
INHIBIT_PACKAGE_DEBUG_SPLIT="1"

DEPENDS += "gstreamer"

PR = "r1"

SRC_URI = "file://${P}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/opt/crank_software
    
    cp -rf ${S}/crank_software/* ${D}/opt/crank_software
}

FILES_${PN} += "/opt/crank_software"
