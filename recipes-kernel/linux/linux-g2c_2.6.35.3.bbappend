# Append path for reach layer to include kernel defconfig 
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC := "${@int(PRINC) + 2}"

SRCREV = "fad856ff65e8c24c6c5fc8b2b225c8a3bd5c973b"
