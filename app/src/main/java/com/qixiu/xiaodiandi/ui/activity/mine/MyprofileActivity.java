package com.qixiu.xiaodiandi.ui.activity.mine;


import android.Manifest;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qixiu.qixiu.request.OKHttpRequestModel;
import com.qixiu.qixiu.request.OKHttpUIUpdataListener;
import com.qixiu.qixiu.request.bean.BaseBean;
import com.qixiu.qixiu.request.bean.C_CodeBean;
import com.qixiu.qixiu.request.parameter.StringConstants;
import com.qixiu.qixiu.utils.PictureCut;
import com.qixiu.qixiu.utils.ToastUtil;
import com.qixiu.wigit.GotoView;
import com.qixiu.wigit.myedittext.IntelligentTextWatcher;
import com.qixiu.wigit.myedittext.TextWatcherAdapterInterface;
import com.qixiu.wigit.zprogress.ZProgressHUD;
import com.qixiu.xiaodiandi.R;
import com.qixiu.xiaodiandi.constant.ConstantUrl;
import com.qixiu.xiaodiandi.engine.PlatformLoginEngine;
import com.qixiu.xiaodiandi.engine.bean.UserInfo;
import com.qixiu.xiaodiandi.model.login.LoginBean;
import com.qixiu.xiaodiandi.model.login.LoginStatus;
import com.qixiu.xiaodiandi.model.mine.UserBean;
import com.qixiu.xiaodiandi.ui.activity.baseactivity.TitleActivity;
import com.qixiu.xiaodiandi.utils.ImageUrlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.wechat.friends.Wechat;
import de.hdodenhof.circleimageview.CircleImageView;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import me.iwf.photopicker.utils.ImageCaptureManager;
import okhttp3.Call;

public class MyprofileActivity extends TitleActivity implements View.OnClickListener, TextWatcherAdapterInterface, OKHttpUIUpdataListener<BaseBean>, PlatformLoginEngine.PlatformResultListener {
    private RelativeLayout relativeLayout_changehead, relativeLayout_changenickname;
    private TextView textView_nickname_change;
    private PopupWindow popwindow;
    //图片选择器参数
    ArrayList<String> selectPhotos = new ArrayList<>();
    int MAX_IMAGE = 1;
    private ImageCaptureManager captureManager;
    //图片路径
    String photoPath;
    private static final String EMPTY_PATH = StringConstants.EMPTY_STRING;
    private CircleImageView circular_head_edit;
    //照相机权限
    String permissions[] = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private IntelligentTextWatcher mIntelligentTextWatcher;
    ZProgressHUD zProgressHUD;


    OKHttpRequestModel okHttpRequestModel;
    private GotoView gotoviewLevel;
    private GotoView gotoViewBindWeichat;
    private GotoView gotoviewPhone;
    private UserBean userBean;

    @Override
    protected void onInitData() {
        okHttpRequestModel = new OKHttpRequestModel(this);
        getData();
    }

    @Override
    protected void onInitViewNew() {
        zProgressHUD = new ZProgressHUD(this);
        captureManager = new ImageCaptureManager(this);
        relativeLayout_changehead = (RelativeLayout) findViewById(R.id.relativeLayout_changehead);
        relativeLayout_changenickname = (RelativeLayout) findViewById(R.id.relativeLayout_changenickname);
        circular_head_edit = (CircleImageView) findViewById(R.id.circular_head_edit);
        textView_nickname_change = (TextView) findViewById(R.id.textView_nickname_change);
        gotoviewLevel = findViewById(R.id.gotoviewLevel);
        gotoviewPhone = findViewById(R.id.gotoviewPhone);
        gotoViewBindWeichat = findViewById(R.id.gotoViewBindWeichat);
        initTitle();
        setClick();
    }

    private void setClick() {
        relativeLayout_changehead.setOnClickListener(this);
        relativeLayout_changenickname.setOnClickListener(this);
    }

    private void initTitle() {
        mTitleView.setTitle("个人信息");
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_myprofile;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativeLayout_changehead:
                if (!hasPermission(permissions)) {
                    hasRequse(1, permissions);
                    return;
                }
                PhotoPicker.builder().setPhotoCount(MAX_IMAGE).setShowCamera(true).setSelected(
                        selectPhotos).start(this);
                break;
            case R.id.relativeLayout_changenickname:
//                showEditPopwindow();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PhotoPicker.REQUEST_CODE || requestCode == PhotoPreview.REQUEST_CODE) {
                //获取图片选择器的图片路径们
                if (data != null) {
                    selectPhotos.clear();
                    List<String> photos =
                            data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                    selectPhotos.addAll(photos);
                    if (selectPhotos.size() < MAX_IMAGE) {
                        circular_head_edit.setImageResource(R.mipmap.authorization_upload_photo);
                    } else {
                        Glide.with(this).load(selectPhotos.get(0)).crossFade().into(circular_head_edit);//todo 上传照片
                        uploadHead(selectPhotos.get(0));
                    }
                }
            } else if (requestCode == ImageCaptureManager.REQUEST_TAKE_PHOTO) {
                captureManager.galleryAddPic();
                photoPath = captureManager.getCurrentPhotoPath();
                selectPhotos.remove(EMPTY_PATH);
                //@author gyh 限制如果达到最大张数拍照后的图片就不添加
                if (selectPhotos.size() < MAX_IMAGE) {
                    selectPhotos.add(photoPath);
                    //selectedBitmap.add(revolvePicture(photoPath));//将拍照获得照片转成Bitmap。添加到集合中
                }
            }
        }
    }

    private void uploadHead(String path) {
        zProgressHUD.show();
        PictureCut.CompressImageWithThumb.callBase64(path, new PictureCut.CompressImageWithThumb.CallBackBase64() {
            @Override
            public void callBack(String base64) {
                if (TextUtils.isEmpty(base64)) {
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("img", base64);
                map.put("uid", LoginStatus.getId());
                okHttpRequestModel.okhHttpPost(ConstantUrl.editProfile, map, new BaseBean());
            }
        });
    }


    EditText editText_edituser;

    private void showEditPopwindow() {
        View view = View.inflate(this, R.layout.popwindow_edituser2, null);
        try {
            popwindow = new PopupWindow(view);
            popwindow.setFocusable(true);
            popwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popwindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            popwindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        } catch (Exception e) {
        }
        RelativeLayout relativeLayout_pop_dismiss = (RelativeLayout) view.findViewById(R.id.relativeLayout_pop_dismiss);
        relativeLayout_pop_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow.dismiss();
            }
        });
        editText_edituser = (EditText) view.findViewById(R.id.editText_edituser);
        editText_edituser.setText(textView_nickname_change.getText().toString());
        editText_edituser.setSelection(textView_nickname_change.getText().toString().length());
        mIntelligentTextWatcher = new IntelligentTextWatcher(getContext(), editText_edituser.getId(), this);
        mIntelligentTextWatcher.setEmojiDisabled(true, editText_edituser);
        editText_edituser.addTextChangedListener(mIntelligentTextWatcher);
        Button button_cancleEdit = (Button) view.findViewById(R.id.button_cancleEdit);
        Button button_confirmEdit = (Button) view.findViewById(R.id.button_confirmEdit);
        button_cancleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popwindow.dismiss();
            }
        });
        button_confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_edituser.getText().toString().length() > 20) {
                    ToastUtil.showToast(MyprofileActivity.this, "输入字数不得超过20位");
                    return;
                }
                if (editText_edituser.getText().toString().trim().equals("")) {
                    ToastUtil.showToast(MyprofileActivity.this, "未传入昵称");
                    return;
                }
                textView_nickname_change.setText(editText_edituser.getText());
                popwindow.dismiss();
            }
        });
    }


    @Override
    public void beforeTextChanged(int editTextId, CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(int editTextId, CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(int editTextId, Editable s) {

    }


    @Override
    protected void onDestroy() {
        if (editText_edituser != null && mIntelligentTextWatcher != null) {
            editText_edituser.removeTextChangedListener(mIntelligentTextWatcher);
        }
        super.onDestroy();
    }

    @Override
    public void onSuccess(BaseBean data, int i) {
        if (data.getUrl().equals(ConstantUrl.editProfile)) {
            zProgressHUD.dismissWithSuccess("修改成功");
        } else {
            zProgressHUD.dismiss();
            if (data instanceof UserBean) {
                userBean = (UserBean) data;
                gotoviewLevel.setSecondText(userBean.getO().getGroup_name());
                Glide.with(getContext()).load(ImageUrlUtils.getFinnalImageUrl(userBean.getO().getAvatar())).into(circular_head_edit);
                gotoviewPhone.setSecondText(userBean.getO().getPhone());
                textView_nickname_change.setText(userBean.getO().getAccount());
                if (userBean.getO().getWechat_user() != 1) {
                    gotoViewBindWeichat.setSecondText("去绑定");
                } else {
                    gotoViewBindWeichat.setSecondText("已绑定");
                }
            }
        }
        if (data.getUrl().equals(ConstantUrl.wxloginUrl)) {
            zProgressHUD.dismissWithSuccess("绑定成功");
            gotoViewBindWeichat.setSecondText("已绑定");
            gotoViewBindWeichat.setEnabled(false);
        }
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        zProgressHUD.dismiss();
    }

    @Override
    public void onFailure(C_CodeBean c_codeBean) {
        zProgressHUD.dismiss();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginStatus.getId());
        okHttpRequestModel.okhHttpPost(ConstantUrl.mineCenter, map, new UserBean());
    }


    //绑定微信
    public void bindWeichat(View view) {
        zProgressHUD.show();
        if (userBean.getO().getWechat_user() != 1) {
            PlatformLoginEngine loginEngine = new PlatformLoginEngine(this);
            loginEngine.startAuthorize(Wechat.NAME);
        } else {
            ToastUtil.toast("您已经绑定了微信");
            zProgressHUD.dismiss();
        }
    }


    @Override
    public void onCancel() {
        zProgressHUD.dismiss();
    }

    @Override
    public void onFailure() {
        zProgressHUD.dismiss();
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        Map<String, String> map = new HashMap();
        map.put("uid", LoginStatus.getId());
        map.put("openid", userInfo.getUserId());
        map.put("nickname", userInfo.getUserName());
        map.put("avatar", userInfo.getUserIcon());
        map.put("device", deviceId);
        map.put("device_type", "1");//设备类型，1、安卓，2、IOS
        map.put("type", "2");//设备类型，1、安卓，2、IOS
        okHttpRequestModel.okhHttpPost(ConstantUrl.wxloginUrl, map, new LoginBean());
    }
}
