/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Logging.Logging;
import static Qombo.Paint.GUI.DrawingBoard.oldShapes;
import static Qombo.Paint.GUI.DrawingBoard.shapes;
import java.awt.Color;
import java.awt.Shape;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author lo2ay
 */
public class MainGUI extends javax.swing.JFrame implements Logging {

    protected JButton strokeButton, fillButton;
    protected int currentAction = 1;
    protected Color strokeColor = Color.black, fillColor = Color.black;
    protected DrawingBoard drawingBoard;

    /**
     * Creates new form MainGUI2
     */
    public MainGUI() {
        initComponents();
        this.currentAction = 1;
        this.fillColor = Color.cyan;
        this.strokeColor = Color.black;
        this.drawingBoard = new DrawingBoard(this);
        this.drawingBoard.setBackground(Color.WHITE);
        this.CanvasPanel.add(drawingBoard);
        this.drawingBoard.setSize(CanvasPanel.getSize().width - 1, CanvasPanel.getSize().height - 1);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rectangleButton = new javax.swing.JButton();
        ellipseButton = new javax.swing.JButton();
        lineButton = new javax.swing.JButton();
        CanvasPanel = new javax.swing.JPanel();
        squareButton = new javax.swing.JButton();
        circleButton = new javax.swing.JButton();
        triangleButton = new javax.swing.JButton();
        deletButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        RedoButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        brushButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rectangleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Rectangle.png"))); // NOI18N
        rectangleButton.setText("Rectangle");
        rectangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rectangleButtonActionPerformed(evt);
            }
        });
        rectangleButton.setIcon(new ImageIcon(MainGUI.class.getResource("Rectangle.png")));

        ellipseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Ellipse.png"))); // NOI18N
        ellipseButton.setText("Ellipse");
        ellipseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ellipseButtonActionPerformed(evt);
            }
        });
        ellipseButton.setIcon(new ImageIcon(MainGUI.class.getResource("Ellipse.png")));

        lineButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Line.png"))); // NOI18N
        lineButton.setText("Line");
        lineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lineButtonActionPerformed(evt);
            }
        });

        CanvasPanel.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout CanvasPanelLayout = new javax.swing.GroupLayout(CanvasPanel);
        CanvasPanel.setLayout(CanvasPanelLayout);
        CanvasPanelLayout.setHorizontalGroup(
            CanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CanvasPanelLayout.setVerticalGroup(
            CanvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        squareButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Square.png"))); // NOI18N
        squareButton.setText("Square");
        squareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                squareButtonActionPerformed(evt);
            }
        });
        squareButton.setIcon(new ImageIcon(MainGUI.class.getResource("Square.png")));

        circleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Circle.png"))); // NOI18N
        circleButton.setText("Circle");
        circleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                circleButtonActionPerformed(evt);
            }
        });
        circleButton.setIcon(new ImageIcon(MainGUI.class.getResource("Circle.png")));

        triangleButton.setText("Triangle");
        triangleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                triangleButtonActionPerformed(evt);
            }
        });

        deletButton.setBackground(java.awt.Color.red);
        deletButton.setText("Delete");
        deletButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletButtonActionPerformed(evt);
            }
        });

        undoButton.setText("Undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });

        RedoButton.setText("Redo");
        RedoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RedoButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        brushButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/brush.png"))); // NOI18N
        brushButton.setText("Brush");
        brushButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brushButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(undoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RedoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(615, 615, 615)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(circleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ellipseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(triangleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(squareButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rectangleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brushButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(undoButton)
                            .addComponent(RedoButton)
                            .addComponent(resetButton)
                            .addComponent(deletButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(triangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(squareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rectangleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ellipseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(circleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brushButton)
                        .addGap(0, 75, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleButtonActionPerformed
        this.currentAction = 1;
    }//GEN-LAST:event_rectangleButtonActionPerformed

    private void ellipseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ellipseButtonActionPerformed
        this.currentAction = 2;
    }//GEN-LAST:event_ellipseButtonActionPerformed

    private void lineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineButtonActionPerformed
        this.currentAction = 3;
    }//GEN-LAST:event_lineButtonActionPerformed

    private void circleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circleButtonActionPerformed
        this.currentAction = 4;
    }//GEN-LAST:event_circleButtonActionPerformed

    private void squareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_squareButtonActionPerformed
        this.currentAction = 5;
    }//GEN-LAST:event_squareButtonActionPerformed

    private void triangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_triangleButtonActionPerformed
        this.currentAction = 6;
    }//GEN-LAST:event_triangleButtonActionPerformed

    private void deletButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletButtonActionPerformed
        // TODO add your handling code here:
        this.currentAction = 7;
    }//GEN-LAST:event_deletButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // TODO add your handling code here:
        this.currentAction = 8;
        if (shapes.size() > 0) {
            try {
                oldShapes.add(shapes.get(shapes.size() - 1));
                shapes.remove(shapes.get(shapes.size() - 1));
                repaint();
            } catch (ArrayIndexOutOfBoundsException ai) {
                System.out.println(ai.getCause());
                ai.printStackTrace();
            }
        }
    }//GEN-LAST:event_undoButtonActionPerformed

    private void RedoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedoButtonActionPerformed
        this.currentAction = 9;
        if (oldShapes.size() > 0) {
            try {
                shapes.add(oldShapes.get(oldShapes.size() - 1));
                oldShapes.remove(oldShapes.get(oldShapes.size() - 1));
                repaint();
            } catch (ArrayIndexOutOfBoundsException ai) {
                System.out.println(ai.getCause());
                ai.printStackTrace();
            }
        }
    }//GEN-LAST:event_RedoButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        this.currentAction = 10;
        for (Shape s : shapes) {
            oldShapes.add(s);
        }
        shapes.clear();
        repaint();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void brushButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brushButtonActionPerformed
        // TODO add your handling code here:
        this.currentAction = 11;
    }//GEN-LAST:event_brushButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CanvasPanel;
    private javax.swing.JButton RedoButton;
    private javax.swing.JButton brushButton;
    private javax.swing.JButton circleButton;
    private javax.swing.JButton deletButton;
    private javax.swing.JButton ellipseButton;
    private javax.swing.JButton lineButton;
    private javax.swing.JButton rectangleButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton squareButton;
    private javax.swing.JButton triangleButton;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
