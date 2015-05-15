FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRCREV = "14c8f7b705de944beb4de3f296506d80871e410f"

SRC_URI += "file://psplash-colors.h \
          file://0001-update-psplash-to-full-screen.patch \
"

do_configure_append () {
    cp ${WORKDIR}/psplash-colors.h ${S}
    touch ${S}/psplash.c
}
