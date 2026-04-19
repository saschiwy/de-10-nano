FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Removed-hw_watchdog_init-prototype-if-not-defined.patch"
SRC_URI += "file://de10-nano-spl.cfg"

