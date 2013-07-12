package jp.co.isken.ocu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class ���Test {

	@BeforeClass
	public static void �f�[�^������() {
		Market.setup();
		Market.setDate(Util.stringToDate("20130627000000"));
	}

	@Test
	public void �������o�^����() {
		��� a = new ���("�X��4");
		assertEquals("�X��4", a.getName());
	}

	@Test
	public void ������o�i�҂Ƃ��ēo�^����() {
		��� a = new ���("�X��5");
		assertEquals(false, a.is�o�i��());
		a.grant�o�i();
		assertEquals(true, a.is�o�i��());

	}

	@Test
	public void �������D�҂Ƃ��ēo�^����() {
		��� a = new ���("�X��6");
		assertEquals(false, a.is���D��());
		a.grant���D();
		assertEquals(true, a.is���D��());
	}

	@Test
	public void �I�[�N�V�����ɏo�i����() throws �o�i�G���[ {
		��� a = new ���("�X��7");
		a.grant�o�i();
		a.�o�i����("ipad1", "20130630120000");
		assertEquals(1, a.getAuctions().size());
		assertEquals("ipad1", a.getAuctions().get(0).getName());

		Date targetdate = Util.stringToDate("20130630120000");
		assertEquals(targetdate, a.getAuctions().get(0).getTimeLimit());
		Market.setup();
	}

	@Test
	public void �o�i�������Ȃ��̂ɃI�[�N�V�����ɏo�i������G���[() {
		��� a = new ���("�X��8");
		try {
			a.�o�i����("ipad2", "");
			fail("exception");
		} catch (�o�i�G���[ e) {
			assertEquals("�o�i����������܂���B", e.getMessage());
		}
	}

	@Test
	public void ���D�ł���() throws �o�i�G���[ {
		��� �o�i�� = new ���("�o�i��");
		�o�i��.grant�o�i();
		�o�i��.�o�i����("ipad2", "20130630000000");
		��� a = new ���("�X��9");
		a.grant���D();
		try {
			a.���D����("ipad2", 100, "20130629000000");
		} catch (���D�G���[ e) {
			fail("exe");
		}
	}

	@Test
	public void ���D���ԊO�̂��߃G���[() throws �o�i�G���[ {
		��� �o�i�� = new ���("�o�i��");
		�o�i��.grant�o�i();
		�o�i��.�o�i����("ipad2", "20130630000000");
		��� a = new ���("�X��9");
		a.grant���D();
		try {
			a.���D����("ipad2", 100, "20130730000000");
			fail();
		} catch (���D�G���[ e) {
			assertEquals("���D���ԊO�ł��B", e.getMessage());
		}
	}

	@Test
	public void ���D�������Ȃ��̂ɃI�[�N�V�����ɓ��D������G���[() {
		��� a = new ���("�X��9");
		try {
			a.���D����("ipad2", 100, "");
			fail("exception");
		} catch (���D�G���[ e) {
			assertEquals("���D����������܂���B", e.getMessage());
		}
	}

	@Test
	public void �I�[�N�V�����̗��D�҂��킩��() throws Exception {
		��� a = new ���("�X��10");
		a.grant�o�i();
		a.�o�i����("ipad3", "20130630000000");

		��� b = new ���("�ق�����");
		b.grant���D();
		b.���D����("ipad3", 100, "20130629000000");

		��� c = new ���("���肫");
		c.grant���D();
		c.���D����("ipad3", 200, "20130629010000");

		Auction auction = Market.getAuction("ipad3");
		assertEquals(auction.getLastTender().get���D��().getName(), "���肫");
	}

	@Test
	public void �o�i�҂�������D�ł��Ȃ�() throws �o�i�G���[ {
		��� a = new ���("���[������");
		a.grant�o�i();
		a.grant���D();
		a.�o�i����("ipad4", "");
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

		��� a = new ���("�X��s");
		a.grant�o�i();
		a.�o�i����("ipadmai", "20130630000000");

		��� b = new ���("�ق�����a");
		b.grant���D();
		try {
			b.���D����("ipadmai", 100, "20130628000000");
		} catch (���D�G���[ e1) {
			fail();
		}
		��� c = new ���("���肫b");
		c.grant���D();
		try {
			c.���D����("ipadmai", 100, "20130629000000");
			fail();
		} catch (���D�G���[ e) {
			assertEquals("�ō����D�z�����������z���w�肵�Ă��������B", e.getMessage());
		}

		Auction auction = Market.getAuction("ipadmai");
		assertEquals(auction.getLastTender().get���D��().getName(), "�ق�����a");
	}

	@Test
	public void �������i���͏o�i�ł��Ȃ�() {
		��� a = new ���("�X��s");
		a.grant�o�i();
		try {
			a.�o�i����("ipad3", "");
		} catch (�o�i�G���[ e) {
			fail();
		}

		��� b = new ���("�ق�����a");
		b.grant�o�i();
		try {
			b.�o�i����("ipad3", "");
			fail();
		} catch (�o�i�G���[ e) {
			assertEquals("���i�����d�����Ă��܂��B", e.getMessage());
		}
	}
}
