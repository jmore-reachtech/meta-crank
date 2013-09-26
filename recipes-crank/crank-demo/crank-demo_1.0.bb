DESCRIPTION = "Crank runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

INSANE_SKIP_${PN} = "ldflags textrel"
INSANE_SKIP_${PN}-dev = "ldflags textrel"
INHIBIT_PACKAGE_DEBUG_SPLIT="1"

PR = "r1"

SRC_URI = "file://${P}.tar.gz"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/application/crank-demo
    install -d ${D}/application/crank-demo/fonts
    install -d ${D}/application/crank-demo/images
    install -d ${D}/application/crank-demo/scripts

    install -m 644 ${WORKDIR}/button/button.gapp ${D}/application/crank-demo/
    install -m 644 ${WORKDIR}/button/fonts/* ${D}/application/crank-demo/fonts/
    install -m 644 ${WORKDIR}/button/images/* ${D}/application/crank-demo/images/
    install -m 644 ${WORKDIR}/button/scripts/* ${D}/application/crank-demo/scripts/
}

FILES_${PN} += "/application/crank-demo /application/crank-demo/fonts /application/crank-demo/images /application/crank-demo/scripts"

