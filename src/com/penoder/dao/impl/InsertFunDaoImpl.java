package com.penoder.dao.impl;

import java.util.List;

import com.penoder.bean.ImgBean;
import com.penoder.bean.TxtBean;
import com.penoder.dao.InsertFunDao;
import com.penoder.operate.InsertGif;
import com.penoder.operate.InsertImg;
import com.penoder.operate.InsertTxt;
import com.penoder.utils.MD5Util;

public class InsertFunDaoImpl implements InsertFunDao {

	@Override
	public String insertGif(List<ImgBean> imgBeans) {
		// TODO Auto-generated method stub
		InsertGif insertGif = new InsertGif();
		String message = "", result = "";
		for (ImgBean imgBean : imgBeans) {
			if (imgBean.getId() == null || "".equals(imgBean.getId())) {	// ID不存在比较内容
				if (!insertGif.selectGifUrl(imgBean.getImg())) {
					// 由于没有ID，所以手动设置ID为 Penoder前缀 加上内容的MD5格式，保证唯一性，也保证不和之前有ID的一致
					message = insertGif.insert("Gif_" + MD5Util.strToMD5(imgBean.getImg()), imgBean.getTitle(), imgBean.getImg(), imgBean.getCt());
				}
			} else {	// ID存在比较ID
				if (!insertGif.selectGif(imgBean.getId())) {
					message = insertGif.insert(imgBean.getId(), imgBean.getTitle(), imgBean.getImg(), imgBean.getCt());
				}
			}
			result = result + message + " 、 ";
		}

		return result.substring(0, result.length()-2);
	}

	@Override
	public String insertImg(List<ImgBean> imgBeans) {
		// TODO Auto-generated method stub
		InsertImg insertImg = new InsertImg();
		String message = "", result = "";
		for (ImgBean imgBean : imgBeans) {
			if (imgBean.getId() == null || "".equals(imgBean.getId())) {	// ID 不存在比较内容
				if (!insertImg.selectImgUrl(imgBean.getImg())) {
					message = insertImg.insert("Img_" + MD5Util.strToMD5(imgBean.getImg()), imgBean.getTitle(), imgBean.getImg(), imgBean.getCt());
				}
			} else {	// ID存在比较ID
				if (!insertImg.selectImg(imgBean.getId())) {
					message = insertImg.insert(imgBean.getId(), imgBean.getTitle(), imgBean.getImg(), imgBean.getCt());
				}
			}
			result = result + message + " 、 ";
		}

		return result.substring(0, result.length()-2);
	}

	@Override
	public String insertTxt(List<TxtBean> txtBeans) {
		// TODO Auto-generated method stub
		InsertTxt insertTxt = new InsertTxt();
		String message = "", result = "";
		for (TxtBean txtBean : txtBeans) {
			if (txtBean.getId() == null || "".equals(txtBean.getId())) {	// ID 不存在比较内容
				if (!insertTxt.selectTxtContent(txtBean.getText())) {
					message = insertTxt.insert("Txt_" + MD5Util.strToMD5(txtBean.getText()), txtBean.getTitle(), txtBean.getText(), txtBean.getCt());
				}
			} else {	// ID存在比较ID
				if (!insertTxt.selectTxt(txtBean.getId())) {
					message = insertTxt.insert(txtBean.getId(), txtBean.getTitle(), txtBean.getText(), txtBean.getCt());
				}
			}
			result = result + message + " 、 ";
		}

		return result.substring(0, result.length()-2);
	}

}
