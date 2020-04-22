def git(){
  sh"""
  rm -rf $$WORKSPACE/maven-project
    git clone https://github.com/jleetutorial/maven-project.git
    cd maven-project
    ls -ltr
    pwd
    """

}
