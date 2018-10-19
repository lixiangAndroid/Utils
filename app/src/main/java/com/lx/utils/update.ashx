<%@ WebHandler Language="C#" Class="update" %>

using System;
using System.IO;
using System.Web;

public class update : IHttpHandler {

    public void ProcessRequest(HttpContext context)
    {


        string path = context.Server.MapPath("~/GBMS.apk");

        //初始化 FileInfo 类的实例，它作为文件路径的包装
        System.IO.FileInfo fi = new FileInfo(path);
        //判断文件是否存在
        if (fi.Exists)
        {
            //将文件保存到本机上
            context.Response.Clear();
            context.Response.AddHeader("Content-Disposition", "attachment; filename=" + context.Server.UrlEncode(fi.Name));
            context.Response.AddHeader("Content-Length", fi.Length.ToString());
            context.Response.ContentType = "application/octet-stream";
            context.Response.Filter.Close();
            context.Response.WriteFile(fi.FullName);
            context.Response.End();
        }
    }

    public bool IsReusable
    {
        get
        {
            return false;
        }
    }

}