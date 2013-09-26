DESCRIPTION = "An image that will launh the Crank demo."

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

IMAGE_INSTALL_append = " \
	${CORE_IMAGE_BASE_INSTALL} \
	packagegroup-custom-core \
	packagegroup-custom-dev-tools \
	bc \
	coreutils \
	usbutils \
	wireless-tools \
	wpa-supplicant \
	linux-firmware-ar9271 \
	linux-firmware-sd8686 \
	linux-firmware-rtl8192cu \
	linux-firmware-rtl8192ce \
	linux-firmware-rtl8192su \
        crank-runtime \
        crank-demo \
        tslib tslib-calibrate tslib-tests tsinit \
        "

inherit core-image

export IMAGE_BASENAME = "reach-image-crank"

