package serialization;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardBuilder {
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private JFrame frame;

	public static void main(String[] args) {
		QuizCardBuilder builder = new QuizCardBuilder();
		builder.go();
	}

	public void go() {
		// build gui
		frame = new JFrame("Quiz Card Builder");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		question = new JTextArea(6, 20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(bigFont);
		JScrollPane qScroller = new JScrollPane(question);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(bigFont);
		JScrollPane aScroller = new JScrollPane(answer);
		aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JButton nextButton = new JButton("Next Card");
		cardList = new ArrayList<QuizCard>();
		JLabel qLabel = new JLabel("Question:");
		JLabel aLabel = new JLabel("Answer:");
		mainPanel.add(qLabel);
		mainPanel.add(qScroller);
		mainPanel.add(aLabel);
		mainPanel.add(aScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());

		/*
		 * We make a menu bar, make a File menu, then put ‘new’ and ‘save’ menu items
		 * into the File menu. We add the menu to the menu bar, then tell the frame to
		 * use this menu bar. Menu items can fire an ActionEvent.
		 */
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
	}

	/*
	 * Inner class Triggered when user hits ‘Next Card’ button; means the user wants
	 * to store that card in the list and start a new card.
	 */
	public class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}
	}

	/*
	 * Inner class Triggered when use chooses ‘Save’ from the File menu; means the
	 * user wants to save all the cards in the current list as a ‘set’ (like,
	 * Quantum Mechanics Set, Hollywood Trivia, Java Rules, etc.). Bring up a file
	 * dialog box and let the user name and save the set.
	 */
	public class SaveMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			QuizCard card = new QuizCard(question.getText(), answer.getText());
			cardList.add(card);
			// Brings up a file dialog box and waits on this line until the user chooses
			// ‘Save’ from the dialog box.
			// All the file dialog navigation and selecting a file, etc. is done for you by
			// the JFileChooser!
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
		}
	}

	/*
	 * Inner class Triggered by choosing ‘New’ from the File menu; means the user
	 * wants to start a brand new set (so we clear out the card list and the text
	 * areas). Clear out the card list, and clear out the text areas.
	 */
	public class NewMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			cardList.clear();
			clearCard();
		}
	}

	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}

	/*
	 * Called by the SaveMenuListener's event handler; does the actual file writing.
	 * Iterate through the list of cards, and write each one out to a text file in a
	 * parseable way (in other words, with clear separations between parts)
	 */
	private void saveFile(File file) {
		try {
			// We chain a BufferedWriter on to a new FileWriter to make writing more
			// efficient.
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			// Walk through the ArrayList of cards and write them out, one card
			// per line, with the question and answer separated by a “/”, and then
			// add a newline character (“\n”)
			for (QuizCard card : cardList) {
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
			}
			writer.close();
		} catch (IOException ex) {
			System.out.println("couldn’t write the cardList out");
			ex.printStackTrace();
		}
	}
}