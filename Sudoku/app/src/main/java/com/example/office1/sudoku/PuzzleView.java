package com.example.office1.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleView extends View {
    private final PuzzleActivity game;

    public PuzzleView(Context context){
        super(context);
        this.game = (PuzzleActivity) context;
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    private float width;
    private float height;
    private int selX;
    private int selY;
    private final Rect selRect = new Rect(); //พื้นที่ของช่องตารางที่เป็นตำแหน่ง cursor

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w /9f;
        height = h / 9f;
        getRect(selX, selY, selRect);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void getRect(int x, int y , Rect rect){
        rect.set((int) (x *width),(int)(y*height),
                (int) (x *width+width),(int)(y*height+height));
    }

    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.puzzle_bg));
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);

        //++วาดเส้นตาราง++
        //กำหนดสีของเส่้นตาราง

        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_dark));

        Paint highlight = new Paint();
        highlight.setColor(getResources().getColor(R.color.puzzle_highlight));

        Paint light = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_light));

        //วาดเส้นตารางย่อย
        for(int i = 0; i < 9; i++){
            canvas.drawLine(0, i*height, getWidth(), i*height, light);
            canvas.drawLine(0, i*height +1, getWidth(), i*height+1, highlight);
            canvas.drawLine(i*width, 0, i*width, getHeight(), light);
            canvas.drawLine(i*width+1, 0, i*width+1, getHeight(), highlight);
        }

        //วาดเส้นตารางหลัก(เส้นแบ่งระหว่างตารางย่อย 3*3)
        for(int i = 0; i < 9; i++){
            if(i % 3 != 0)
                continue;
                canvas.drawLine(0, i*height, getWidth(), i*height, dark);
                canvas.drawLine(0, i*height +1, getWidth(), i*height+1, highlight);
                canvas.drawLine(i*width, 0, i*width, getHeight(), dark);
                canvas.drawLine(i*width+1, 0, i*width+1, getHeight(), highlight);
        }

        //++วาดตัวเลข++
        //กำหนดสีและรูปแบบของตัวอักษร
        Paint fg = new Paint(Paint.ANTI_ALIAS_FLAG);
        fg.setColor(getResources().getColor(R.color.puzzle_fg));
        fg.setStyle(Paint.Style.FILL);
        fg.setTextSize(height * 0.75f);
        fg.setTextScaleX(width / height);
        fg.setTextAlign(Paint.Align.CENTER);

        //วาดตัวเลขกึ่งกลางช่องตาราง
        Paint.FontMetrics fm = fg.getFontMetrics();
        float x = width /2;
        float y = height / 2;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                canvas.drawText(this.game.getTileString(i,j), i*width+x, j*height+y, fg);
            }
        }

        Paint selected = new Paint();
        selected.setColor(getResources().getColor(R.color.puzzle_selected));
        canvas.drawRect(selRect, selected);

        //++วาดตัวช่วย++
        //อ่านค่าสี 3 สีที่มช้วาดตัวช่วยจากรีซอร์สมาเก็บในอาเรย์ c
        int c[]={getResources().getColor(R.color.puzzle_hint0),
                getResources().getColor(R.color.puzzle_hint1),
                getResources().getColor(R.color.puzzle_hint2)};
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int numleft = 9 - game.getUsedTiles(i,j).length; //หาว่าเหลือตัวเลขกี่ตัวที่สามารถเติมลงในแต่ละช่องได้
                if(numleft < c.length){ //ถ้าเหลือน้อยกว่า 3 จะวาดสี่เหลี่ยมทับลงช่องนั้น
                    Rect r = new Rect();
                    getRect(i,j,r);

                    Paint hint = new Paint();
                    hint.setColor(c[numleft]);
                    canvas.drawRect(r, hint);
                }
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_DPAD_UP:
                select(selX ,selY -1);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                select(selX ,selY + 1);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                select(selX - 1 ,selY);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                select(selX + 1,selY);
                break;
            case KeyEvent.KEYCODE_0:
            case KeyEvent.KEYCODE_SPACE:
                setSelectedTile(0);
                break;
            case KeyEvent.KEYCODE_1:
                setSelectedTile(1);
                break;
            case KeyEvent.KEYCODE_2:
                setSelectedTile(2);
                break;
            case KeyEvent.KEYCODE_3:
                setSelectedTile(3);
                break;
            case KeyEvent.KEYCODE_4:
                setSelectedTile(4);
                break;
            case KeyEvent.KEYCODE_5:
                setSelectedTile(5);
                break;
            case KeyEvent.KEYCODE_6:
                setSelectedTile(6);
                break;
            case KeyEvent.KEYCODE_7:
                setSelectedTile(7);
                break;
            case KeyEvent.KEYCODE_8:
                setSelectedTile(8);
                break;
            case KeyEvent.KEYCODE_9:
                setSelectedTile(9);
                break;
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_DPAD_CENTER:
                game.showKeypadOrError(selX, selY);
                break;
            default:
                return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    //invalidate ถ้าไม่ใส argument แอนดรอยด์จะวาดหน้าจอให้ใหม่ทั้งหน้าจอ
    public void select(int x, int y){
        invalidate(selRect);
        selX = Math.min(Math.max(x, 0), 8);
        selX = Math.min(Math.max(y, 0), 8);
        getRect(selX, selY, selRect);
        invalidate(selRect);
    }

    public boolean onTochEvent(MotionEvent event){
        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);

        select((int) (event.getX()/width),((int)(event.getY()/height)));
        game.showKeypadOrError(selX, selY);
        return true;
    }

    public void setSelectedTile(int num){
        if(game.setTileIfValid(selX, selY, num)){
            invalidate();
        }
    }
}
