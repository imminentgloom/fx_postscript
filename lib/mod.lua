--         Postscript for paratext
--
--          Based on Delayyyyyyyy
--               by cfdrake
--
-- Modified and ported to fx mod for norns
--            by imminent gloom

local fx = require("fx/lib/fx")
local mod = require 'core/mods'

local FxPostscript = fx:new{
    subpath = "/fx_postscript"
}

function FxPostscript:add_params()
    params:add_separator("fx_postscript", "postscript")
	FxPostscript:add_slot("fx_postscript_slot", "slot")
	FxPostscript:add_control("fx_postscript_time", "time", "time", controlspec.new(0.001, 2, "exp", 0, 0.55, "s"))
	FxPostscript:add_control("fx_postscript_feedback", "feedback", "feedback", controlspec.new(0, 100, "lin", 0, 80, "%"))
	FxPostscript:add_control("fx_postscript_width", "width", "width", controlspec.new(0, 100, "lin", 0, 0, "%"))
	FxPostscript:add_control("fx_postscript_send", "send", "send", controlspec.new(0, 100, "lin", 0, 80, "%"))
	FxPostscript:add_control("fx_postscript_hp", "hp", "hp", controlspec.new(20, 10000, "exp", 0, 20, "Hz"))
	FxPostscript:add_control("fx_postscript_lp", "lp", "lp", controlspec.new(20, 10000, "exp", 0, 5000, "Hz"))
end

mod.hook.register("script_post_init", "postscript mod post init", function()
    FxPostscript:add_params()
    FxPostscript_init() -- add this to your script if you want to set anything up after the mod loads!
end)

mod.hook.register("script_post_cleanup", "postscript mod post cleanup", function()
end)

return FxPostscript
