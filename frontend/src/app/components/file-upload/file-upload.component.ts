import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  fileUploadUrl = "http://localhost:8080/product/upload";
  file: any;
  flag = true;

  constructor(
    private _http: HttpClient
  ) { }

  ngOnInit(): void {
  }

  selectFile(event: any) {
    // console.log(event);
    this.file = event.target.files[0];
    console.log(this.file);
  }

  uploadFile() {
    let formData = new FormData();
    formData.append("file", this.file);
    this.flag = false;
    this._http.post(this.fileUploadUrl, formData).subscribe((data: any) => {
      this.flag = true;
      alert(data.message);
    }, (error) => {
      this.flag = true;
      alert("Error occure!!");
    });
  }
}
