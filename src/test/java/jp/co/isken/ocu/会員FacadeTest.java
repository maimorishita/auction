package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import jp.co.isken.ocu.domain.Item;
import jp.co.isken.ocu.domain.Market;
import jp.co.isken.ocu.domain.Member;
import jp.co.isken.ocu.service.���Facade;
import jp.co.isken.ocu.util.Util;
import jp.co.isken.ocu.util.���D�G���[;
import jp.co.isken.ocu.util.�o�i�G���[;

import org.junit.Before;
import org.junit.Test;

public class ���FacadeTest {
	
	@Before
	public void doBefore() {
		Market.setup();
		Market.setDate(Util.stringToDate("20130627000000"));
	}

	@Test
	public void ������o�i�҂Ƃ��ēo�^����() {
		Member a = new Member("�X��5");
		assertEquals(false, a.is�o�i��());
		���Facade.grant�o�i(a);
		assertEquals(true, a.is�o�i��());

	}

	@Test
	public void �������D�҂Ƃ��ēo�^����() {
		Member a = new Member("�X��6");
		assertEquals(false, a.is���D��());
		���Facade.grant���D(a);
		assertEquals(true, a.is���D��());
	}

	@Test
	public void �I�[�N�V�����ɏo�i����() throws �o�i�G���[ {
		Member a = new Member("�X��7");
		���Facade.grant�o�i(a);
		a.�o�i����("ipad1", "20130630120000", 200000);
		assertEquals(1, a.getItems().size());
		assertEquals("ipad1", a.getItems().get(0).getName());

		Date targetdate = Util.stringToDate("20130630120000");
		assertEquals(targetdate, a.getItems().get(0).getTimeLimit());
		Market.setup();
	}

	@Test
	public void �o�i�������Ȃ��̂ɃI�[�N�V�����ɏo�i������G���[() {
		Member a = new Member("�X��8");
		try {
			a.�o�i����("ipad2", "", 200000);
			fail("exception");
		} catch (�o�i�G���[ e) {
			assertEquals("�o�i����������܂���B", e.getMessage());
		}
	}

	@Test
	public void ���D�ł���() throws �o�i�G���[ {
		Member �o�i�� = new Member("�o�i��");
		���Facade.grant�o�i(�o�i��);
		�o�i��.�o�i����("ipad2", "20130630000000", 200000);
		Member a = new Member("�X��9");
		���Facade.grant���D(a);
		try {
			a.���D����("ipad2", 100, "20130629000000");
		} catch (���D�G���[ e) {
			fail("exe");
		}
	}

	@Test
	public void ���D���ԊO�̂��߃G���[() throws �o�i�G���[ {
		Member �o�i�� = new Member("�o�i��");
		���Facade.grant�o�i(�o�i��);
		�o�i��.�o�i����("ipad2", "20130630000000", 200000);
		Member a = new Member("�X��9");
		���Facade.grant���D(a);
		try {
			a.���D����("ipad2", 100, "20130730000000");
			fail();
		} catch (���D�G���[ e) {
			assertEquals("���D���ԊO�ł��B", e.getMessage());
		}
	}

	@Test
	public void ���D�������Ȃ��̂ɃI�[�N�V�����ɓ��D������G���[() {
		Member a = new Member("�X��9");
		try {
			a.���D����("ipad2", 100, "");
			fail("exception");
		} catch (���D�G���[ e) {
			assertEquals("���D����������܂���B", e.getMessage());
		}
	}

	@Test
	public void �I�[�N�V�����̗��D�҂��킩��() throws Exception {
		Member a = new Member("�X��10");
		���Facade.grant�o�i(a);
		a.�o�i����("ipad3", "20130630000000", 200000);

		Member b = new Member("�ق�����");
		���Facade.grant���D(b);
		b.���D����("ipad3", 100, "20130629000000");

		Member c = new Member("���肫");
		���Facade.grant���D(c);
		c.���D����("ipad3", 200, "20130629010000");

		Item item = Item.getItem("ipad3");
		assertEquals(item.getLastTender().get���D��().getName(), "���肫");
	}

	@Test
	public void �o�i�҂�������D�ł��Ȃ�() throws �o�i�G���[ {
		Member a = new Member("���[������");
		���Facade.grant�o�i(a);
		���Facade.grant���D(a);
		a.�o�i����("ipad4", "", 200000);
		try {
			a.���D����("ipad4", 100, "");
			fail("Exception is expected");
		} catch (���D�G���[ e) {
			assertEquals("�o�i�҂͓��D�ł��܂���B", e.getMessage());
		}
	}

	@Test
	public void �ŐV�̓��D�z�����������z�łȂ��Ɠ��D�ł��Ȃ�() throws �o�i�G���[ {
		Market.setup();

		Member a = new Member("�X��s");
		���Facade.grant�o�i(a);
		a.�o�i����("ipadmai", "20130630000000", 200000);

		Member b = new Member("�ق�����a");
		���Facade.grant���D(b);
		try {
			b.���D����("ipadmai", 100, "20130628000000");
		} catch (���D�G���[ e1) {
			fail();
		}
		Member c = new Member("���肫b");
		���Facade.grant���D(c);
		try {
			c.���D����("ipadmai", 100, "20130629000000");
			fail();
		} catch (���D�G���[ e) {
			assertEquals("�ō����D�z�����������z���w�肵�Ă��������B", e.getMessage());
		}

		Item item = Item.getItem("ipadmai");
		assertEquals(item.getLastTender().get���D��().getName(), "�ق�����a");
	}

	@Test
	public void �������i���͏o�i�ł��Ȃ�() {
		Member a = new Member("�X��s");
		���Facade.grant�o�i(a);
		try {
			a.�o�i����("ipad3", "", 200000);
		} catch (�o�i�G���[ e) {
			fail();
		}

		Member b = new Member("�ق�����a");
		���Facade.grant�o�i(b);
		try {
			b.�o�i����("ipad3", "", 200000);
			fail();
		} catch (�o�i�G���[ e) {
			assertEquals("���i�����d�����Ă��܂��B", e.getMessage());
		}
	}


}
