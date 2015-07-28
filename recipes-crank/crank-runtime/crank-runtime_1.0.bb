DESCRIPTION = "Crank runtime"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

INSANE_SKIP_${PN} = "ldflags textrel"
INSANE_SKIP_${PN}-dev = "ldflags textrel"
INSANE_SKIP_${PN}-dev = "staticdev" 
INHIBIT_PACKAGE_DEBUG_SPLIT="1"

DEPENDS = "freetype"

PR = "r1"

SRC_URI = "file://${P}.tar.gz \
  file://crankinit \
"

S = "${WORKDIR}/crank_runtime"

do_install() {
    install -d ${D}/Crank
    install -d ${D}/Crank/bin
    install -d ${D}/Crank/include
    install -d ${D}/Crank/plugin
    install -d ${D}/Crank/lib

    install -m 755 ${S}/bin/* ${D}/Crank/bin/
    install -m 644 ${S}/plugins/* ${D}/Crank/plugin/
    install -m 644 ${S}/lib/* ${D}/Crank/lib/
    cp -r ${S}/include/* ${D}/Crank/include/

    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d
    install ${WORKDIR}/crankinit ${D}${sysconfdir}/init.d
}

inherit update-rc.d

INITSCRIPT_NAME = "crankinit"
INITSCRIPT_PARAMS = "start 99 5 2 . stop 19 0 1 6 ."

FILES_${PN} += "/Crank/bin /Crank/lib /Crank/plugin /Crank/include"
FILES_${PN}-dev += "/Crank/lib/*.a "
