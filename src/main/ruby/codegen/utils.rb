
require 'erb'

module Codegen

  module Utils

    # FunctionStart : '(T1, T2, T3)', probably followed by ' => ...'
    def self.fs(num_end, prefix = "T", num_start: 1)
      str = (num_start..num_end).to_a.map { |t| "#{prefix}#{t}" }.join(", ")
      num_end - num_start == 0 ? str : "(#{str})"
    end

    # Expand
    def self.exp(num_end, prefix = "T", num_start: 1)
      (num_start..num_end).to_a.map { |t| "#{prefix}#{t}" }.join(", ")
    end

    # Repeat
    def self.rp(t, str)
      ([str] * t).join(", ")
    end

  end

end
