
require_relative '../utils'
require 'fileutils'

module Codegen
  
  module Apply

    @erb = ERB.new(File.read(File.expand_path("../../erb/apply.scala.erb", __FILE__)), trim_mode: "%<>")

    def self.run(scala_root, codegen_package, max)
      puts("> Generating 'apply'")
      puts("    max: #{max}")
      puts

      apply_package = "#{codegen_package}.apply"
      apply_dir = File.join(scala_root, *apply_package.split("."))
      apply_file = File.join(apply_dir, "apply.scala")

      FileUtils.mkdir_p(apply_dir)
      File.open(apply_file, "w") { |f| f.puts(@erb.result(binding)) }
      nil
    end

  end
  
end
