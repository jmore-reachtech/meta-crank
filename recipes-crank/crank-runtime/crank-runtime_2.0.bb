DESCRIPTION = "Crank runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

INSANE_SKIP_${PN} = "ldflags textrel"
INSANE_SKIP_${PN}-dev = "ldflags textrel"
INHIBIT_PACKAGE_DEBUG_SPLIT="1"

DEPENDS = "freetype"

PR = "r1"

SRC_URI = "file://${P}.tar.gz \
  file://crankinit-circles \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/usr/bin
    install -d ${D}/usr/lib
    install -d ${D}/usr/lib/plugin

    install -m 755 ${WORKDIR}/${P}/bin/* ${D}/usr/bin/
    install -m 644 ${WORKDIR}/${P}/plugins/* ${D}/usr/lib/plugin/
    install -m 644 ${WORKDIR}/${P}/lib/* ${D}/usr/lib/

    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d
    install crankinit-circles ${D}${sysconfdir}/init.d/crankinit
}

inherit update-rc.d

INITSCRIPT_NAME = "crankinit"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 19 0 1 6 ."

FILES_${PN} += "/usr/bin /usr/lib /usr/lib/plugin"

