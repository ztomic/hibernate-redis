package org.hibernate.examples.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

/**
 * org.hibernate.examples.utils.BinarySerializer
 *
 * @author 배성혁 sunghyouk.bae@gmail.com
 * @since 2013. 11. 28. 오후 5:34
 */
@Slf4j
public class BinarySerializer {

    public byte[] serialize(Object graph) {
        if (graph == null)
            return new byte[]{};

        try {
            @Cleanup ByteArrayOutputStream bos = new ByteArrayOutputStream();
            @Cleanup ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(graph);
            oos.flush();

            return bos.toByteArray();
        } catch (Exception e) {
            log.error("객체 직렬화에 실패했습니다. graph=" + graph, e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0)
            return (T) null;

        try {
            @Cleanup ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            @Cleanup ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (Exception e) {
            log.error("객체 역직렬화에 실패했습니다.", e);
            throw new RuntimeException(e);
        }
    }
}
