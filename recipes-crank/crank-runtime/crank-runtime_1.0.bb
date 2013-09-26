DESCRIPTION = "Crank runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

INSANE_SKIP_${PN} = "ldflags textrel"
INSANE_SKIP_${PN}-dev = "ldflags textrel"
INHIBIT_PACKAGE_DEBUG_SPLIT="1"

DEPENDS = "freetype"

PR = "r1"

SRC_URI = "file://${P}.tar.gz \
  file://crankinit \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/usr/bin
    install -d ${D}/usr/lib
    install -d ${D}/usr/lib/plugin

    install -m 755 ${WORKDIR}/bin/* ${D}/usr/bin/
    install -m 644 ${WORKDIR}/plugins/* ${D}/usr/lib/plugin/
    install -m 644 ${WORKDIR}/lib/* ${D}/usr/lib/

    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d
    install crankinit ${D}${sysconfdir}/init.d
}

inherit update-rc.d

INITSCRIPT_NAME = "crankinit"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 19 0 1 6 ."

FILES_${PN} += "/usr/bin /usr/lib /usr/lib/plugin"

