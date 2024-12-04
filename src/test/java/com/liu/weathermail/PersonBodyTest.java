package com.liu.weathermail;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.PageReadListener;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.weathermail.dao.BodyInfoV2Dao;
import com.liu.weathermail.dao.PersonInfoDao;
import com.liu.weathermail.dao.SysDictDao;
import com.liu.weathermail.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import com.alibaba.excel.EasyExcel;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootTest
public class PersonBodyTest {

    @Resource
    private PersonInfoDao personInfoDao;


    @Resource
    private BodyInfoV2Dao bodyInfoV2Dao;

    @Resource
    private SysDictDao sysDictDao;

    @Test
    public void total() {
        // Excel 文件路径
        String filePath = "/Users/misteryliu/Documents/2024-1101-2024-11-30/人体数据处理/人体外部形态尺寸数据21777.xlsx"; // 替换为你的 Excel 文件路径

        // 存储读取的数据
        List<TotalRowData> dataList = new ArrayList<>();

        // 使用 EasyExcel 读取
        EasyExcel.read(filePath, TotalRowData.class, new PageReadListener<TotalRowData>(data -> {
            dataList.addAll(data);
        })).sheet("总体").headRowNumber(2).doRead();
        dataList.removeIf(data -> Objects.isNull(data.getBh()));
        dataList.sort(Comparator.comparing(TotalRowData::getBh));

        // 打印读取结果
        TotalRowData row = null;
        for (int i = 0; i < dataList.size(); i++) {
            try {
                row = dataList.get(i);
                PersonInfoEntity personInfoEntity = new PersonInfoEntity();
                BeanUtils.copyProperties(row, personInfoEntity);
                personInfoEntity.setSex(Objects.equals(row.getExcelGender(), "男") ? 1L : 0L);
                personInfoEntity.setId(UUID.randomUUID().toString());
                personInfoEntity.setBh(row.getBh().toString());
                personInfoDao.insert(personInfoEntity);
                System.out.println(personInfoEntity);
            } catch (Exception e) {
                System.out.println("转换出错: " + row.getBh() + "e: " + e);
            }
        }
    }


    @Test
    public void body() {
        String filePath = "/Users/misteryliu/Documents/2024-1101-2024-11-30/人体数据处理/人体内部骨骼尺寸数据21777.xlsx";
        bodyDataProcess(filePath, "下肢", "person_lowerlimb_inner");
    }


    public void bodyDataProcess(String filePath, String sheetName, String type) {

        List<BodyInfoRow> dataList = new ArrayList<>();
        AtomicInteger rowCounter = new AtomicInteger(0);  // 用于保证行顺序

        // 逐行读取并处理数据
        EasyExcel.read(filePath, getBodyInfoRowDataClass(type), new AnalysisEventListener<BodyInfoRow>() {

                    // 逐行读取 Excel 数据时调用此方法
                    @Override
                    public void invoke(BodyInfoRow data, AnalysisContext context) {
                        // 获取当前行号，并通过 AtomicInteger 记录顺序
                        int rowIndex = rowCounter.getAndIncrement();
                        data.setRowIndex(++rowIndex);
                        // 通过行号来确保顺序，将数据按照行顺序添加
                        dataList.add(data);
                    }

                    // 读取完毕时调用此方法
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        System.out.println("所有数据读取完成！" + sheetName);
                        dataList.sort(Comparator.comparing(BodyInfoRow::getRowIndex));
                        // 数据读取完成后，按顺序打印
                        for (BodyInfoRow data : dataList) {
                            System.out.println("开始处理数据第" + data.getRowIndex());
                            BodyInfoV2Entity bodyInfoV2Entity = new BodyInfoV2Entity();
                            BeanUtils.copyProperties(data, bodyInfoV2Entity);
                            bodyInfoV2Entity.setType(type);
                            Wrapper<PersonInfoEntity> wrapper = new QueryWrapper<PersonInfoEntity>()
                                    .eq("bh", String.valueOf(data.getRowIndex()));
                            // 按照编号查询person数据，处理数据
                            PersonInfoEntity personInfoEntity = personInfoDao.selectOne(wrapper);
                            bodyInfoV2Entity.setId(personInfoEntity.getId());

                            bodyInfoV2Dao.insert(bodyInfoV2Entity);
                        }
                    }
                })
                .sheet(sheetName)           // 指定工作表名称
                .headRowNumber(2)        // 设置数据从第 3 行开始（跳过前两行）
                .doRead();               // 执行读取操作
    }

    // 根据不同的type动态获取HeadRowData的Class类型
    private Class<? extends BodyInfoRow> getBodyInfoRowDataClass(String type) {
        // 这里根据type返回不同的HeadRowData子类
        switch (type) {
            case "person_head":
                return HeadRowData.class;
            case "person_neck":
                return NickRowData.class;
            case "person_uppertorso":
                return UppertorsoRowData.class;
            case "person_lowertrunk":
                return LowerTorsoRowData.class;
            case "person_thigh":
                return ThighRowData.class;
            case "person_shank":
                return LowerLegRowData.class;
            case "person_upperarm":
                return UpperArmRowData.class;
            case "person_forearm":
                return ForearmRowData.class;
            case "person_hand":
                return HandRowData.class;
            case "person_foot":
                return FootRowData.class;
            case "person_head_inner":
                return SkullRowData.class;
            case "person_torso_inner":
                return RibRowData.class;
            case "person_upperlimb_inner":
                return ShoulderAndPelvisRowData.class;
            case "person_lowerlimb_inner":
                return PelvisAndLegsRowData.class;
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }
}



