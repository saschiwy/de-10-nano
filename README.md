# DE10-Nano Yocto Build Repository

This repository provides a Yocto-based build environment to run a Terasic DE10-Nano module.

Base:
- Yocto Release: Scarthgap
- Target board: Terasic DE10-Nano (Cyclone V SoC)

## Prerequisites

- Linux build host
- Git
- Sufficient disk space (Yocto build artifacts are large)

## Clone Repository and Checkout Submodules

After cloning, the submodules must be initialized:

```bash
git clone <repo-url>
cd de10-nano-2
git submodule update --init --recursive
```

If the repository is already cloned, this is enough:

```bash
git submodule update --init --recursive
```

## Initialize Build Environment

```bash
source poky/oe-init-build-env build
```

## Build Image

```bash
bitbake core-image-minimal
```

The build outputs are located at:

- build/tmp/deploy/images/de10-nano

Typical important artifacts are:

- core-image-minimal-de10-nano.rootfs.wic
- u-boot-with-spl.sfp

## Flash SD Card

Example using dd:

```bash
sudo dd if=build/tmp/deploy/images/de10-nano/core-image-minimal-de10-nano.rootfs.wic of=/dev/sdX bs=4M conv=fsync status=progress
sync
```

Note:
- Replace /dev/sdX with the correct target device.

## Goal

With this repository, you can build and run a bootable Yocto system for the Terasic DE10-Nano.
