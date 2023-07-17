package utils;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.Timer;

public class SwingFader {
	
	public static void fadeIn(final Window dialog) {
        final Timer timer = new Timer(50, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 0;
            @Override public void actionPerformed(ActionEvent e) {
                opacity += 0.20f;
                dialog.setOpacity(Math.min(opacity, 1));
                if (opacity >= 1) timer.stop();
            }
        });

        dialog.setOpacity(0);
        timer.start();
        dialog.setVisible(true);
    }

	public static void fadeOut(final Window dialog) {
        final Timer timer = new Timer(50, null);
        timer.setRepeats(true);
        timer.addActionListener(new ActionListener() {
            private float opacity = 1;
            @Override public void actionPerformed(ActionEvent e) {
                opacity -= 0.20f;
                dialog.setOpacity(Math.max(opacity, 0));
                if (opacity <= 0) {
                    timer.stop();
                    dialog.dispose();
                }
            }
        });

        dialog.setOpacity(1);
        timer.start();
	}

}
