_ [HOME](http://openoldowl.github.io/stonix)
_ [GITHUB](https://github.com/OpenOldOwl/stonix)
_ [JENKINS](http://server.bearbone.com.br:8888/)
_ [TRAVIS](https://travis-ci.org/OpenOldOwl/stonix)
_

[![Build Status](http://server.bearbone.com.br:8888/buildStatus/icon?job=stonix)](http://server.bearbone.com.br:8888/job/stonix/)
[![Travis branch](https://img.shields.io/travis/OpenOldOwl/stonix/develop.svg?maxAge=2592000)](https://travis-ci.org/OpenOldOwl/stonix)
[![Coveralls branch](https://img.shields.io/coveralls/OpenOldOwl/stonix/develop.svg?maxAge=2592000)](https://coveralls.io/github/OpenOldOwl/stonix?branch=develop)
[![Dependency Status](https://www.versioneye.com/user/projects/570a4af2fcd19a00415b103b/badge.svg?style=flat)](https://www.versioneye.com/user/projects/570a4af2fcd19a00415b103b)

[![Github All Releases](https://img.shields.io/github/downloads/OpenOldOwl/stonix/total.svg?maxAge=2592000)](https://github.com/OpenOldOwl/stonix/)
[![GitHub issues](https://img.shields.io/github/issues/OpenOldOwl/stonix.svg)](https://github.com/OpenOldOwl/stonix/issues)
[![GitHub forks](https://img.shields.io/github/forks/OpenOldOwl/stonix.svg)](https://github.com/OpenOldOwl/stonix/network)
[![GitHub stars](https://img.shields.io/github/stars/OpenOldOwl/stonix.svg)](https://github.com/OpenOldOwl/stonix/stargazers)

production mode

    gradle
    clean core production bootRepackage bootRun

development mode

    gradle
    clean core development bootRun

test mode

    gradle
    clean core alltests test
