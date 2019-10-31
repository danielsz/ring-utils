(set-env! :source-paths   #{"src"}
          :resource-paths   #{"src"}
          :dependencies '[[ring "1.7.1" :scope "provided"]
                          [compojure "1.6.1" :scope "provided"]])

(task-options!
 push {:repo-map {:url "https://clojars.org/repo/"}}
 pom {:project 'org.danielsz/ring-utils
      :version "0.1.3"
      :scm {:name "git"
            :url "https://github.com/danielsz/ring-utils"}})

(deftask build
  []
  (comp (pom) (jar) (install)))

(deftask push-release
  []
  (comp
   (build)
   (push)))
