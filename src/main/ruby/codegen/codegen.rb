
require_relative 'scripts/apply'

module Codegen

  SCALA_ROOT = File.expand_path("../../../scala", __FILE__)
  CODEGEN_PACKAGE = "puddle.codegen"

  puts("=====| Running Codegen |=====")
  puts

  Apply.run(SCALA_ROOT, CODEGEN_PACKAGE, 20)

end
