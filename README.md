# cloudbees

selenium-java-allure-sample project implements cloudbees website automation. It is written as a maven project using selenium java. Have incorporated allure reporting. I coudlnt use homebrew allure as it is not supported in mac os 12 anymore. I tried installing using a zip file and it appears to be not picking up steps. 


GitRepoUpdater.java is a java class that will use default values to clone repo, add files and commit it if run with -silent argument. Will take command line input if ran without it
Written under the assumption that git repo uses ssh authentication and the host keys are in place
