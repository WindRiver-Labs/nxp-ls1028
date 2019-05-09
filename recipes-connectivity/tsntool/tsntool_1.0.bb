#
# Copyright (C) 2019 Wind River Systems, Inc.
#

SUMMARY = "TSN"
HOMEPAGE = "https://source.codeaurora.org/external/qoriq/qoriq-components/tsntool"
DESCRIPTION = "This package contains the TSN tool program from NXP"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef58f855337069acd375717db0dbbb6d"

inherit pkgconfig

DEPENDS = " readline libnl"
PV = "1.0"

SRCREV = "f08ab21874b76fb9395ef30f0ab1b6b994d51f1f"
SRC_URI += "https://source.codeaurora.org/external/qoriq/qoriq-components/tsntool_1.0.tar.gz \
	   file://0001-tsntool-include-tsn.h-by-patch.patch \
	   file://0002-tsntool-fix-errors-for-missing-format-parameters.patch \
	   file://0003-tsntool-remove-rpath-for-compiling-errors.patch \
	   "

SRC_URI[md5sum] = "0bdc5e8e48bcb34c14907e951c31db12"
SRC_URI[sha256sum] = "4e284c68f0327761eb8828366c5f0c8d687acd3fc581a53e3da3de554984a6ae"

KERNELDIR = "${STAGING_KERNEL_DIR}"
S = "${WORKDIR}/tsntool"
B = "${S}"

COMPATIBLE_MACHINE = "nxp-ls1028"

do_install_append () {
    install -d ${D}${libdir}
    install -m 755 ${S}/libtsn.so ${D}${libdir}
    install -d ${D}${bindir}
    install -m 755 ${S}/tsntool ${D}${bindir}
}

FILES_${PN} =  "${bindir}/* ${libdir}/*"
FILES_${PN}-dev = ""


INSANE_SKIP_${PN}-dev += "dev-elf"
INSANE_SKIP_${PN} += "dev-so"
