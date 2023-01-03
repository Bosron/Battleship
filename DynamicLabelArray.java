package battleship;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


class DynamicLabelArray {

        private JLabel[] labels = new JLabel[0];

        public DynamicLabelArray(int length) {
            labels = new JLabel[length];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = new JLabel();
                labels[i].setBounds(0, 0, 1, 1);
            }
        }

        public void setlabels(JLabel[] arr) {
            for (int i = 0; i < labels.length; i++) {
                this.labels[i] = arr[i];
            }
        }

        public void addLabel() {
            JLabel[] arr = new JLabel[labels.length + 1];
            for (int i = 0; i < labels.length; i++) {
                arr[i] = labels[i];

            }
            labels = new JLabel[arr.length];
            setlabels(arr);
        }

        public void removeLabel(int elementNumber) {

            if (elementNumber >= 0 && elementNumber < labels.length) {
                JLabel[] arr = new JLabel[labels.length - 1];
                for (int i = 0, y = 0; i < labels.length; i++, y++) {

                    if (i == elementNumber) {
                        labels[i].setVisible(false);

                        if (elementNumber == labels.length - 1) {

                        } else {
                            y--;
                        }
                    } else {
                        arr[y] = labels[i];
                    }

                }
                labels = new JLabel[arr.length];
                setlabels(arr);

            } else {
                System.out.println("Error: not able to remove element out of array length");
            }
        }

        public int length() {
            return labels.length;
        }

        public void spawnLabel(int numberInArray, int x, int y,int width, int height, ImageIcon labelGhostIcon) {
            labels[numberInArray] = new JLabel();
            labels[numberInArray].setIcon( labelGhostIcon);
            labels[numberInArray].setBounds(x, y, width, height);
            labels[numberInArray].setOpaque(false);
        }

        public void boundsSetter(int numberInArray, int x, int y, int width, int height) {
            labels[numberInArray].setBounds(x, y, width, height);
        }

        public void iconSetter(int numberInArray, ImageIcon icon) {
            labels[numberInArray].setIcon(icon);
        }

        public JLabel elementGetter(int numberInArray) {
            return labels[numberInArray];
        }

    }
