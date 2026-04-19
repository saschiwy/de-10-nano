SUMMARY = "Minimal DE10-Nano FPGA demo bitstream + loader"
DESCRIPTION = "Installs fpga.rbf and a Device Tree overlay (fpga.dtbo) into \
/lib/firmware/, plus a /usr/bin/load-fpga helper that configures the FPGA \
fabric from the HPS at runtime using the Linux FPGA Manager + configfs."
LICENSE = "CLOSED"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = " \
    file://fpga.rbf \
    file://fpga-overlay.dts \
    file://load-fpga.sh \
"

S = "${WORKDIR}"

DEPENDS = "dtc-native"

do_compile() {
    dtc -O dtb -o ${WORKDIR}/fpga.dtbo -b 0 -@ ${WORKDIR}/fpga-overlay.dts
}

do_install() {
    install -d ${D}/lib/firmware
    install -m 0644 ${WORKDIR}/fpga.rbf  ${D}/lib/firmware/fpga.rbf
    install -m 0644 ${WORKDIR}/fpga.dtbo ${D}/lib/firmware/fpga.dtbo

    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/load-fpga.sh ${D}${bindir}/load-fpga
}

FILES:${PN} = " \
    /lib/firmware/fpga.rbf \
    /lib/firmware/fpga.dtbo \
    ${bindir}/load-fpga \
"
