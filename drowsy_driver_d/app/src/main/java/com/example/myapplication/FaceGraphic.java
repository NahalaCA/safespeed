package com.example.myapplication;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;

import org.json.JSONObject;

class FaceGraphic extends GraphicOverlay.Graphic implements JsonResponse {
    private static final int EYES_CLOSED_MAX = 2;
    private static final int EYE_CLOSE_COLOR = -65536;
    private static final int EYE_OPEN_COLOR = -16711936;
    private static final float EYE_OPEN_THRESHOLD = 0.6f;
    private static final int EYE_RECT_RADIUS = 30;
    private static final float EYE_STROKE_WIDTH = 10.0f;
    private static final Style EYE_STYLE = Style.STROKE;
    private static final int FACE_COLOR = -16776961;
    private static final int FACE_RECT_RADIUS = 30;
    private static final float FACE_STROKE_WIDTH = 10.0f;
    private static final Style FACE_STYLE = Style.STROKE;
    private int mBothEyesClosedCounter = 0;
    private Paint mEyeClosePaint;
    private Paint mEyeOpenPaint;
    private volatile Face mFace;
    private Paint mFacePaint;
    private Handler mMsgHandler;
    private int totalAlertCount = 0;

    private int bothEyesClosedCounter = 0;

    FaceGraphic(GraphicOverlay overlay, Handler msgHandler) {
        super(overlay);
        this.mMsgHandler = msgHandler;
        this.mFacePaint = new Paint();
        this.mFacePaint.setColor(FACE_COLOR);
        this.mFacePaint.setStyle(FACE_STYLE);
        this.mFacePaint.setStrokeWidth(FACE_STROKE_WIDTH);
        this.mEyeOpenPaint = new Paint();
        this.mEyeOpenPaint.setColor(EYE_OPEN_COLOR);
        this.mEyeOpenPaint.setStyle(EYE_STYLE);
        this.mEyeOpenPaint.setStrokeWidth(FACE_STROKE_WIDTH);
        this.mEyeClosePaint = new Paint();
        this.mEyeClosePaint.setColor(EYE_CLOSE_COLOR);
        this.mEyeClosePaint.setStyle(EYE_STYLE);
        this.mEyeClosePaint.setStrokeWidth(FACE_STROKE_WIDTH);
    }

    void updateFace(Face face) {
        this.mFace = face;
        postInvalidate();
    }

    private void sendAlert() {
        Message msg = this.mMsgHandler.obtainMessage();
        msg.what = 0;
        this.mMsgHandler.sendMessage(msg);
        this.mMsgHandler.sendEmptyMessageDelayed(1, 2000);
    }

    public void draw(Canvas canvas) {
        if (this.mFace != null) {
            canvas.drawRoundRect(new RectF(translateX(this.mFace.getPosition().x + (this.mFace.getWidth() / 2.0f)) - scaleX(this.mFace.getWidth() / 2.0f), translateY(this.mFace.getPosition().y + (this.mFace.getHeight() / 2.0f)) - scaleY(this.mFace.getHeight() / 2.0f), translateX(this.mFace.getPosition().x + (this.mFace.getWidth() / 2.0f)) + scaleX(this.mFace.getWidth() / 2.0f), translateY(this.mFace.getPosition().y + (this.mFace.getHeight() / 2.0f)) + scaleY(this.mFace.getHeight() / 2.0f)), 30.0f, 30.0f, this.mFacePaint);
            boolean left_eye_closed = false;
            boolean right_eye_closed = false;
            for (Landmark landmark : this.mFace.getLandmarks()) {
                float px;
                float py;
                if (landmark.getType() == 4) {
                    px = translateX(landmark.getPosition().x);
                    py = translateY(landmark.getPosition().y);
                    if (isEyeOpen(this.mFace.getIsLeftEyeOpenProbability())) {
                        canvas.drawCircle(px, py, 30.0f, this.mEyeOpenPaint);
                    } else {
                        canvas.drawLine(px - 30.0f, py - 30.0f, px + 30.0f, py + 30.0f, this.mEyeClosePaint);
                        canvas.drawLine(px - 30.0f, py + 30.0f, px + 30.0f, py - 30.0f, this.mEyeClosePaint);
                        left_eye_closed = true;
                    }
                } else if (landmark.getType() == 10) {
                    px = translateX(landmark.getPosition().x);
                    py = translateY(landmark.getPosition().y);
                    if (isEyeOpen(this.mFace.getIsRightEyeOpenProbability())) {
                        canvas.drawCircle(px, py, 30.0f, this.mEyeOpenPaint);
                    } else {
                        canvas.drawLine(px - 30.0f, py - 30.0f, px + 30.0f, py + 30.0f, this.mEyeClosePaint);
                        canvas.drawLine(px - 30.0f, py + 30.0f, px + 30.0f, py - 30.0f, this.mEyeClosePaint);
                        right_eye_closed = true;
                    }
                }
            }

// Inside your detection logic
            if (left_eye_closed && right_eye_closed) {
                bothEyesClosedCounter++;

                // Check if both eyes have been closed consecutively for a certain number of times
                if (bothEyesClosedCounter >= 5) {
                    // Reset the counter
                    bothEyesClosedCounter = 0;

                    // Trigger the alert
                    sendAlert();

                    // Make your network request or perform other actions here
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) FaceGraphic.this;
                    String q = "/add_ditection_face?log_id=" + login.logid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            } else {
                // Reset the counter if at least one eye is open
                bothEyesClosedCounter = 0;
            }
//
//            if (left_eye_closed && right_eye_closed) {
//                this.mBothEyesClosedCounter++;
//
//
//                /////addddding content here
//            } else {
//                this.mBothEyesClosedCounter = 0;
//            }
//            if (this.mBothEyesClosedCounter > EYES_CLOSED_MAX) {
//                this.mBothEyesClosedCounter = 0;
//                totalAlertCount++;
//                if (totalAlertCount >= 5) {
//                    // Trigger the alert
//                    sendAlert();
////                    sh= PreferenceManager.getDefaultSharedPreferences(Context);
//                    JsonReq JR=new JsonReq();
//                    JR.json_response=(JsonResponse)FaceGraphic.this;
////        String q = "/viewrating?log_id=" + sh.getString("log_id","")+"&am_id="+User_view_request.am_id;
//                    String q = "/add_ditection_face?log_id=" +login.logid;
//                    q=q.replace(" ", "%20");
//                    JR.execute(q);
//                    totalAlertCount=0;
//
//                }
//            }
//            else {
//                this.mBothEyesClosedCounter = 6;
//            }
        }
    }

    boolean isEyeOpen(float eyeOpenProbability) {
        if (eyeOpenProbability > EYE_OPEN_THRESHOLD) {
            return true;
        }
        return false;
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
//                Toast.makeText(getApplicationContext(),"Registration Succesfull",Toast.LENGTH_LONG).show();
//                ContextCompat.startActivity(new Intent(getApplicationContext(),Login.class));
            }
        }
        catch (Exception e) {
            // TODO: handle exception

//            Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();

        }
    }
}
