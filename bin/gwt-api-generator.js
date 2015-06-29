#!/usr/bin/env node
'use strict';
var gulp = require('gulp')
require('../gulpfile');

require('coa').Cmd()
  .name(process.argv[1])
  .title('gwt-api-generator')
  .helpful()
  .opt()
    .name('package')
    .req()
    .title('Bower package(s) to use. Multiple packages can be defined with: package="foo bar" or package=foo,bar')
    .long('package')
    .end()
  .opt()
    .name('Java directory')
    .title('Directory where the Java files are generated. (Default: src/main/java)')
    .long('javaDir')
    .end()
  .opt()
    .name('Resources directory')
    .title('Directory where the Resource files are generated. (Default: src/main/resources)')
    .long('resourcesDir')
    .end()
  .opt()
    .name('Group Id')
    .title('Group id of the project. (Default: com.vaadin.components.gwt.polymer)')
    .long('groupId')
    .end()
  .opt()
    .name('Artifact Id')
    .title('Artifact id of the project. (Default: gwt-polymer-elements)')
    .long('artifactId')
    .end()
  .opt()
    .name('Version')
    .title('Version of the generated maven project. (Defaults to package.json.pom.version || pakage.json.version)')
    .long('version')
    .end()
  .opt()
    .name('pom')
    .flag()
    .title('Adds a pom.xml file to the root directory to enable easy packaging')
    .long('pom')
    .end()
  .act(function() {
    gulp.start('default');
  })
  .end()
  .run(process.argv.slice(2));