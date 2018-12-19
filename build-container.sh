#!/bin/bash
#
# Copyright (c) 2018 AT&T Intellectual Property. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#        http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

DOCKER_REPO='nexus3.akraino.org:10003'
STAGING_BUILD=${STAGING_BUILD:=''}
AUTOSTAGING=${AUTOSTAGING:=''}

set -e -u -x -o pipefail

CON_NAME='akraino-portal'
if [ -n "$STAGING_BUILD" -a -n "$AUTOSTAGING" ]
then
    # For a staging build, the $VERSION is fixed
    VERSION=`xmlstarlet sel -N "x=http://maven.apache.org/POM/4.0.0" -t -v "/x:project/x:version" AECPortalMgmt/pom.xml`
    VERSION=$(echo "$VERSION" | sed 's/-SNAPSHOT//')
    WARFILE="${NEXUS_URL}/content/repositories/autostaging-${AUTOSTAGING}/org/akraino/${PROJECT}/${VERSION}/${PROJECT}-${VERSION}.war"
    DOCKER_REPO='nexus3.akraino.org:10004'
    curl -O "${WARFILE}"
else
    # For a snapshot build - find the latest snapshot
    VERSION=`xmlstarlet sel -N "x=http://maven.apache.org/POM/4.0.0" -t -v "/x:project/x:version" AECPortalMgmt/pom.xml`
    XMLFILE="${NEXUS_URL}/service/local/repositories/snapshots/content/org/akraino/${PROJECT}/${VERSION}/maven-metadata.xml"
    curl -O "${XMLFILE}"
    V2=`grep value maven-metadata.xml | sed -e 's;</value>;;' -e 's;.*<value>;;' | uniq`
    WARFILE="${NEXUS_URL}/service/local/repositories/snapshots/content/org/akraino/${PROJECT}/${VERSION}/${PROJECT}-${V2}.war"
    curl -O "${WARFILE}"
fi

ln $(basename ${WARFILE}) AECPortalMgmt.war

# Append stream, if it is not the master stream
if [ "${STREAM}" != "master" ]
then
    VERSION="${VERSION}-${STREAM}"
fi

# Build and push the Docker container
docker build -f Dockerfile -t ${CON_NAME}:${VERSION} .
docker tag ${CON_NAME}:${VERSION} ${DOCKER_REPO}/${CON_NAME}:${VERSION}
docker push ${DOCKER_REPO}/${CON_NAME}:${VERSION}
