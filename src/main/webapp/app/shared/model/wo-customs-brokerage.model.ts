import { IWoTransportModes } from 'app/shared/model/wo-transport-modes.model';

export interface IWoCustomsBrokerage {
  id?: number;
  monthlyImportShipment?: string;
  monthlyExportShipment?: string;
  shipmentValue?: string;
  productInfo?: string;
  woTransportModes?: IWoTransportModes[];
  woWorkOrderId?: number;
}

export class WoCustomsBrokerage implements IWoCustomsBrokerage {
  constructor(
    public id?: number,
    public monthlyImportShipment?: string,
    public monthlyExportShipment?: string,
    public shipmentValue?: string,
    public productInfo?: string,
    public woTransportModes?: IWoTransportModes[],
    public woWorkOrderId?: number
  ) {}
}
