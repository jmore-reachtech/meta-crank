DESCRIPTION = "A minimal qt5 image without X11"

LICENSE = "MIT"

include qt5-versions.inc

TOUCH = "tslib tslib-calibrate tslib-tests"

IMAGE_INSTALL_append = "\
    ${CORE_IMAGE_BASE_INSTALL} \
    ${TOUCH} \
    packagegroup-custom-core \
    packagegroup-custom-dev-tools-gdb \
    packagegroup-custom-dev-tools \
    firmware-imx-vpu-imx6d \
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
    qtbase-fonts \
    qtbase-plugins \
    qtbase-tools \
    qtdeclarative \
    qtdeclarative-plugins \
    qtdeclarative-tools \
    qtdeclarative-qmlplugins \
    qtmultimedia \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
    qtsvg \
    qtsvg-plugins \
    qtsensors \
    qtimageformats-plugins \
    qtsystems \
    qtsystems-tools \
    qtsystems-qmlplugins \
    qtscript \
    qt3d \
    qt3d-examples \
    qt3d-qmlplugins \
    qt3d-tools \
    qtwebkit \
    qtwebkit-qmlplugins \
    imx-test \    
    reach-qml-viewer-qt5 \
    reach-sio-agent \
    reach-tio-agent \
    reach-eio-agent \
    alsa-utils \
    alsa-state \
    psplash \
    reach-qml-plugins-imx \
    reach-qml-demo-qt5 \
    i2c-tools \
    strace \
    wireless-tools \
    canutils \
"

inherit core-image

do_rootfs[depends] += " mtd-utils-native:do_populate_sysroot "

export IMAGE_BASENAME="reach-image-qt5"
export UBI_VOLNAME="${MACHINE}-app"
export IMAGE_LOC="${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}"
export APP_DIR_SIZE="51200"

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
