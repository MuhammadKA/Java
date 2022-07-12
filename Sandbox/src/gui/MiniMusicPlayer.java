package gui;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MiniMusicPlayer {

	static JFrame f = new JFrame("My First Music Video");
	static MyDrawPanel m1;

	public static void main(String[] args) {
		MiniMusicPlayer mini = new MiniMusicPlayer();
		mini.go();
	} // close method

	public void setUpGui() {
		m1 = new MyDrawPanel();
		f.setContentPane(m1);
		f.setBounds(30, 30, 300, 300);
		f.setVisible(true);
	} // close method

	public void go() {
		setUpGui();

		try {

			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			/*
			 * Register with events with the sequencer. The event registration method takes
			 * the listener AND an int array representing the list of ControllerEvents you
			 * want. We want only one event #127.
			 */
			sequencer.addControllerEventListener(m1, new int[] {127});
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			int r = 0;
			for (int i = 0; i < 60; i+= 4) {
				r = (int) ((Math.random() * 50) + 1);
				track.add(makeEvent(144, 1, r, 100, i));
				/*
				 * Here's how we pick up the beat. We inserted our OWN ControllerEvent (176 says
				 * the event type is ControllerEvent with an argument for event number #127.
				 * This event will do NOTHING. We put it JUST so that we can get an event each
				 * time a note is played. We can't listen for NOTE ON/OFF events. We're making
				 * this event happen at the SAME tick as the NOTE ON.
				 */
				track.add(makeEvent(176, 1, 127, 0, i));
				track.add(makeEvent(128, 1, r, 100, i + 2));
			} // end loop

			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(120);
			sequencer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // close method

	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	} // close method

	class MyDrawPanel extends JPanel implements ControllerEventListener {

		boolean msg = false;

		@Override
		public void controlChange(ShortMessage event) {
			msg = true;
			repaint();
		}

		public void paintComponenet(Graphics g) {
			if (msg) {
				int r = (int) (Math.random() * 250);
				int gr = (int) (Math.random() * 250);
				int b = (int) (Math.random() * 250);

				g.setColor(new Color(r, gr, b));

				int ht = (int) ((Math.random() * 120) + 10);
				int width = (int) ((Math.random() * 120) + 10);

				int x = (int) ((Math.random() * 40) + 10);
				int y = (int) ((Math.random() * 40) + 10);

				g.fillRect(x, y, ht, width);
				msg = false;
			} // close if
		} // close method

	} // close inner class
} // close outer class
