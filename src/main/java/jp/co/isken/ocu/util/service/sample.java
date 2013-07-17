package jp.co.isken.ocu.util.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import jp.co.isken.ocu.domain.Tender;

import org.apache.commons.io.IOUtils;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class sample {

	public void main(List<Tender> tenders){

	FileOutputStream fos = null;
	try {
	    ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
	    // �O���t�f�[�^��ݒ肷��
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    for(Tender t :tenders){
	    	 dataset.addValue(t.getAmount(), "", t.getDate().toString());
	    }


	    // �O���t�𐶐�����
	    JFreeChart chart = ChartFactory.createLineChart("���D�z", "�o�i��", "���z", dataset, PlotOrientation.VERTICAL, true, false, false);
	    // �w�i�F��ݒ�
	    chart.setBackgroundPaint(ChartColor.WHITE);
	    // �t�@�C���֏o�͂���
	    fos = new FileOutputStream(this.getClass().getSimpleName() + ".jpg");
	    System.out.println(this.getClass().getSimpleName() + ".jpg");
	    ChartUtilities.writeChartAsJPEG(fos, chart, 600, 400);

	} catch (IOException e) {
	    // �G���[����
	} finally {
	    IOUtils.closeQuietly(fos);
	}
	}
}
