DESCRIPTION = "Image including the AxessTMC Edge OS"

require recipes-core/images/core-image-minimal.bb

# rng-tools is used to speed up /dev/random. This is used by Monit to generate a
# random id. Without it, it can in some cases (Pi 4) take minutes to start up!
IMAGE_INSTALL += " \
    packagegroup-base \
    ca-certificates \
    rng-tools \
    bash \
    sudo \
    iptables \
    ntp \
    opkg \
    swupdate \
    swupdate-tools \
    u-boot-fw-utils \
    lua \
    monit \
    tcpdump \
"

inherit extrausers

EXTRA_USERS_PARAMS = "useradd -P admin admin;"

ROOTFS_POSTPROCESS_COMMAND += "add_releaseinfo; "

add_releaseinfo () {
    echo "${DISTRO_VERSION}" > ${IMAGE_ROOTFS}${sysconfdir}/version
}
