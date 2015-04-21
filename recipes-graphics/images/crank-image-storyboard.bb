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
        crank-runtime \
        crank-runtime-dev \
        crank-demo \
        tslib tslib-calibrate tslib-tests tsinit \
        packagegroup-fsl-gstreamer \
        gstreamer \
        gst-plugins-base-app \
        gst-plugins-base \
        gst-plugins-good \
        gst-plugins-good-rtsp \
        gst-plugins-good-udp \
        gst-plugins-good-rtpmanager \
        gst-plugins-good-rtp \
        gst-plugins-good-video4linux2 \
"

inherit core-image

export IMAGE_BASENAME = "crank-image-storyboard"

do_rootfs[depends] += " mtd-utils-native:do_populate_sysroot "

export UBI_VOLNAME="${MACHINE}-app"
export IMAGE_LOC="${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}"
export APP_DIR_SIZE="500000"

multi_part () {
        # do ext3
        dd if=/dev/zero of=${IMAGE_LOC}.app.ext3 seek=${APP_DIR_SIZE} count=0 bs=1k
        mkfs.ext3 -F ${IMAGE_LOC}.app.ext3 -d ${IMAGE_ROOTFS}/application/

        # do config
        echo \[ubifs\] > ubinize.cfg
        echo mode=ubi >> ubinize.cfg
        echo image=${IMAGE_LOC}.app.ubifs >> ubinize.cfg
        echo vol_id=0 >> ubinize.cfg
        echo vol_type=dynamic >> ubinize.cfg
        echo vol_name=${UBI_VOLNAME} >> ubinize.cfg
        echo vol_flags=autoresize >> ubinize.cfg
    
        # do ubifs & ubi
        mkfs.ubifs -r ${IMAGE_ROOTFS}/application/ -m $APP_DIR_SIZE -o ${IMAGE_LOC}.app.ubifs ${MKUBIFS_ARGS}
        ubinize -o ${IMAGE_LOC}.app.ubi ${UBINIZE_ARGS} ubinize.cfg 

        # remove from rootfs
        rm -r ${IMAGE_ROOTFS}/application/*

}

ROOTFS_POSTPROCESS_COMMAND += " multi_part ; "

