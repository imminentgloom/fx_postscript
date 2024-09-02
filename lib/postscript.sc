//         Postscript for paratext
//
//          Based on Delayyyyyyyy
//               by cfdrake
//
// Modified and ported to fx mod for norns
//            by imminent gloom

FxPostscript : FxBase {

	*new {
		var ret = super.newCopyArgs(nil, \none, (
			time: 0.55,
			feedback: 80,
			width: 0,
			send: 20,
			highpass: 20,
			lowpass: 5000

		), nil, 0.5);
		^ret;
	}

	*initClass {
		FxSetup.register(this.new);
	}

	subPath {
		^"/fx_postscript";
	}

	symbol {
		^\FxPostscript;
	}

	addSynthdefs {
		SynthDef(\FxPostscript, {|inBus, outBus|

            var t, w, f, s;
            var input, fb, output;

            t = \time.kr(0.55, 0.2);
            w = \width.kr(0.0, 0.2) * 0.01 * 0.01;
            f = \feedback.kr(80, 0.2) * 0.01;
            s = \send.kr(80, 0.2) * 0.01;

            //mix two inputs so "insert" mode picks up the nb_voice and live input
            //live input * 2 to match levels (idk why)
			input = Mix.ar([In.ar(inBus, 2), SoundIn.ar([0, 1]) * 2]);
			fb = LocalIn.ar(2);
			output = LeakDC.ar((fb * f) + (input * s));
            output = HPF.ar(output, \highpass.kr(20, 0.2));
			output = LPF.ar(output, \lowpass.kr(5000, 0.2));
			output = output.tanh;
            output = DelayC.ar(output, 5, LFNoise2.ar(12).range([t, t + w], [t + w, t])).reverse;
			LocalOut.ar(output);

            Out.ar(outBus, output);
		}).add;
	}

}
