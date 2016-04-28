/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Logging.Logging;
import Qombo.Paint.Core.ShapeArrayList;
import Qombo.Paint.Shapes.Shape;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

/**
 *
 * @author lo2ay
 */
public class MainGUI extends javax.swing.JFrame implements Logging {

    protected int currentAction = 1;
    protected static Color outlineColor = Color.black, fillColor = Color.black;

    public static Color getOutlineColor() {
        return outlineColor;
    }

    public static Color getFillColor() {
        return fillColor;
    }
    protected DrawingBoard drawingBoard;

    /**
     * Creates new form MainGUI2
     */
    public MainGUI() {
        super("Paint My Ass");
        initComponents();
        this.currentAction = 1;
        this.fillColor = Color.cyan;
        this.outlineColor = Color.black;
        this.drawingBoard = new DrawingBoard(this);
        this.drawingBoard.setBackground(Color.WHITE);
        this.CanvasPanel.add(drawingBoard);
        this.drawingBoard.setSize(CanvasPanel.getSize().width - 1, CanvasPanel.getSize().height - 1);
        this.setResizable(false);
        this.fillColorButton.setBackground(fillColor);
        this.outlineColorButton.setBackground(outlineColor);
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
        fillColorButton = new javax.swing.JButton();
        outlineColorButton = new javax.swing.JButton();
        modeLabel = new javax.swing.JLabel();
        currentModeLabel = new javax.swing.JLabel();
        helperLabel = new javax.swing.JLabel();
        reColorButton = new javax.swing.JButton();

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
            .addGap(0, 596, Short.MAX_VALUE)
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

        undoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Undo.png"))); // NOI18N
        undoButton.setText("Undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });

        RedoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Qombo/Paint/GUI/Redo.png"))); // NOI18N
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

        fillColorButton.setText("Fill Color");
        fillColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillColorButtonActionPerformed(evt);
            }
        });

        outlineColorButton.setText("Stroke Color");
        outlineColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outlineColorButtonActionPerformed(evt);
            }
        });

        modeLabel.setText("Current mode:");

        currentModeLabel.setText("Recantgle drawing mode.");

        helperLabel.setText("helper label");
        helperLabel.setVisible(false);

        reColorButton.setText("Re-Color");
        reColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reColorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reColorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(undoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(RedoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deletButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(modeLabel)
                                .addGap(27, 27, 27)
                                .addComponent(currentModeLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(314, 314, 314)
                                .addComponent(helperLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CanvasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(circleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ellipseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(triangleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(squareButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rectangleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(fillColorButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(outlineColorButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CanvasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(resetButton)
                            .addComponent(deletButton)
                            .addComponent(RedoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fillColorButton)
                            .addComponent(modeLabel)
                            .addComponent(currentModeLabel)
                            .addComponent(undoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outlineColorButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(reColorButton)
                        .addComponent(helperLabel))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rectangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rectangleButtonActionPerformed
        this.currentAction = 1;
        this.currentModeLabel.setText("Recantgle drawing mode.");
        this.helperLabel.setVisible(false);
    }//GEN-LAST:event_rectangleButtonActionPerformed

    private void ellipseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ellipseButtonActionPerformed
        this.currentAction = 2;
        this.currentModeLabel.setText("Ellipse drawing mode.");
        this.helperLabel.setVisible(false);
    }//GEN-LAST:event_ellipseButtonActionPerformed

    private void lineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lineButtonActionPerformed
        this.currentAction = 3;
        this.currentModeLabel.setText("Line drawing mode.");
        this.helperLabel.setVisible(false);
    }//GEN-LAST:event_lineButtonActionPerformed

    private void circleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_circleButtonActionPerformed
        this.currentAction = 4;
        this.currentModeLabel.setText("Circle drawing mode.");
        this.helperLabel.setVisible(false);
    }//GEN-LAST:event_circleButtonActionPerformed

    private void squareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_squareButtonActionPerformed
        this.currentAction = 5;
        this.currentModeLabel.setText("Square drawing mode.");
        this.helperLabel.setVisible(false);
    }//GEN-LAST:event_squareButtonActionPerformed

    private void triangleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_triangleButtonActionPerformed
        this.currentAction = 6;
        this.currentModeLabel.setText("Triangle drawing mode.");
        this.helperLabel.setVisible(true);
        this.helperLabel.setText("Click three times on the canvas. Current click is #1");
    }//GEN-LAST:event_triangleButtonActionPerformed

    private void deletButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletButtonActionPerformed
        this.currentAction = 7;
        this.currentModeLabel.setText("Delete mode.");
        this.helperLabel.setVisible(true);
        this.helperLabel.setText("Click on a shape to delete it.");
    }//GEN-LAST:event_deletButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        if (!this.drawingBoard.undoHistory.empty()) {
            ShapeArrayList<Shape> undo = this.drawingBoard.undoHistory.pop();
            this.drawingBoard.shapes.clear();
            this.drawingBoard.shapes = undo;
            this.drawingBoard.redoHistory.push(undo);
            this.drawingBoard.repaint();
            log(this.drawingBoard.shapes.size());
        }
    }//GEN-LAST:event_undoButtonActionPerformed

    private void RedoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RedoButtonActionPerformed
        if (!this.drawingBoard.redoHistory.empty()) {
            ShapeArrayList<Shape> redo = this.drawingBoard.redoHistory.pop();
            this.drawingBoard.shapes.clear();
            this.drawingBoard.shapes = redo;
            this.drawingBoard.undoHistory.push(redo);
            this.drawingBoard.repaint();
            log(this.drawingBoard.shapes.size());
        }
    }//GEN-LAST:event_RedoButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        this.drawingBoard = new DrawingBoard(this);
        repaint();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void fillColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillColorButtonActionPerformed
        fillColor = JColorChooser.showDialog(null, "Choose Fill Color", fillColor);
        this.fillColorButton.setBackground(fillColor);
    }//GEN-LAST:event_fillColorButtonActionPerformed

    private void outlineColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outlineColorButtonActionPerformed
        outlineColor = JColorChooser.showDialog(null, "Choose Stroke Color", outlineColor);
        this.outlineColorButton.setBackground(outlineColor);
    }//GEN-LAST:event_outlineColorButtonActionPerformed

    private void reColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reColorButtonActionPerformed
        this.currentAction = 10;
        this.currentModeLabel.setText("recoloring mode.");
        this.helperLabel.setVisible(true);
        this.helperLabel.setText("Choose a color first then click on shape to recolor it.");
    }//GEN-LAST:event_reColorButtonActionPerformed

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
    private javax.swing.JButton circleButton;
    private javax.swing.JLabel currentModeLabel;
    private javax.swing.JButton deletButton;
    private javax.swing.JButton ellipseButton;
    private javax.swing.JButton fillColorButton;
    protected javax.swing.JLabel helperLabel;
    private javax.swing.JButton lineButton;
    private javax.swing.JLabel modeLabel;
    private javax.swing.JButton outlineColorButton;
    private javax.swing.JButton reColorButton;
    private javax.swing.JButton rectangleButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton squareButton;
    private javax.swing.JButton triangleButton;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
